package com.example.contactapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.data.Contact
import com.example.contactapp.data.ContactDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val dao: ContactDao
):ViewModel() {
    private val isSortedByDateAdded = MutableStateFlow(true)

    private var contacts =
        isSortedByDateAdded.flatMapLatest { sort ->
            if (sort) {
                dao.getContactsOrderdByDateAdded()
            } else {
                dao.getContactsOrderdByName()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(ContactState())
    val state =
        combine(_state, isSortedByDateAdded, contacts) { state, isSortedByDateAdded, contacts ->
            state.copy(
                contacts = contacts
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent(event: ContactsEvent) {
        when (event) {
            is ContactsEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }

            is ContactsEvent.SaveContact -> {
                val contact = Contact(
                    name = state.value.name.value,
                    phone = state.value.phone.value,
                    mobile = state.value.mobile.value,
                    email = state.value.email.value,
                    dateAdded = System.currentTimeMillis()
                )

                viewModelScope.launch {
                    dao.upsertContact(contact)
                }

                _state.update {
                    it.copy(
                        name = mutableStateOf(""),
                        phone = mutableStateOf("")
                    )
                }


            }

            ContactsEvent.SortContacts -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
        }
    }
}