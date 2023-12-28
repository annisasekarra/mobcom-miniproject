package com.example.contactapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contactapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    state: ContactState,
    navController: NavController,
    onEvent: (ContactsEvent) -> Unit
) {

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.weight(1f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                IconButton(onClick = { onEvent(ContactsEvent.SortContacts) }) {
                    Icon(
                        imageVector = Icons.Rounded.Sort,
                        contentDescription = "Sort Notes",
                        modifier = Modifier.size(35.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.name.value = ""
                state.phone.value = ""
                state.mobile.value = ""
                state.email.value = " "
                navController.navigate("AddContactScreen")
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add new note")
            }
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.contacts.size) { index ->
                ContactItem(
                    state = state,
                    index = index,
                    onEvent = onEvent
                )
            }

        }

    }

}

@Composable
fun ContactItem(
    state: ContactState,
    index: Int,
    onEvent: (ContactsEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = state.contacts[index].name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.contacts[index].phone,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.contacts[index].mobile,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.contacts[index].email,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )



        }

        IconButton(
            onClick = {
                onEvent(ContactsEvent.DeleteContact(state.contacts[index]))
            }
        ) {

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Contact",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )

        }

    }
}