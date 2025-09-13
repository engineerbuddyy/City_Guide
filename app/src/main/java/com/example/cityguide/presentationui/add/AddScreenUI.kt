package com.example.cityguide.presentationui.add

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddScreenUI(
    navController: NavController,
    viewModel: AddScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (state.error != null) {
            Text(text = state.error!!, color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = state.placeName,
            onValueChange = { viewModel.onValueChange(placeName = it) },
            label = { Text("Place Name") }
        )

        OutlinedTextField(
            value = state.description,
            onValueChange = { viewModel.onValueChange(description = it) },
            label = { Text("Description") }
        )

        OutlinedTextField(
            value = state.category,
            onValueChange = { viewModel.onValueChange(category = it) },
            label = { Text("Category") }
        )

        OutlinedTextField(
            value = state.address,
            onValueChange = { viewModel.onValueChange(address = it) },
            label = { Text("Address") }
        )

        OutlinedTextField(
            value = state.rating,
            onValueChange = { viewModel.onValueChange(rating = it) },
            label = { Text("Rating") }
        )

        Button(
            onClick = { viewModel.addPlace { navController.popBackStack() } },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isSaving
        ) {
            if (state.isSaving) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Add Place")
            }
        }
    }
}
