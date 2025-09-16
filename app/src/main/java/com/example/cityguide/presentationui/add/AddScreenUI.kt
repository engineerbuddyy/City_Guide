package com.example.cityguide.presentationui.add

import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cityguide.R

@Composable
fun AddScreenUI(
    navController: NavController,
    viewModel: AddScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current


    val pickImage = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            val byteArray = inputStream?.readBytes()
            inputStream?.close()
            viewModel.onValueChange(image = byteArray)
        }
        Log.d("AddScreenUI", "Selected Image URI: $uri")
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (state.error != null) {
                Text(text = state.error!!, color = MaterialTheme.colorScheme.error)
            }

            if(state.image != null){
                state.image?.let {
                    Image(
                        bitmap = BitmapFactory.decodeByteArray(it, 0, it.size).asImageBitmap(),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 8.dp)
                    )
                }

            }
            else{

                Image(
                    painter = painterResource(id = R.drawable.mainimg),
                    contentDescription = "Sample Image",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 8.dp)
                )

            }

            Button(
                onClick = { pickImage.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFda0c49))
            ) {
                Text("Pick Image")
            }

            OutlinedTextField(
                value = state.placeName,
                onValueChange = { viewModel.onValueChange(placeName = it) },
                label = { Text("Place Name") },
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            OutlinedTextField(
                value = state.description,
                onValueChange = { viewModel.onValueChange(description = it) },
                label = { Text("Description") },
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            OutlinedTextField(
                value = state.category,
                onValueChange = { viewModel.onValueChange(category = it) },
                label = { Text("Category") },
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            OutlinedTextField(
                value = state.address,
                onValueChange = { viewModel.onValueChange(address = it) },
                label = { Text("Address") },
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            OutlinedTextField(
                value = state.rating,
                onValueChange = { viewModel.onValueChange(rating = it) },
                label = { Text("Rating (0-5)") },
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            Button(
                onClick = { viewModel.addPlace { navController.popBackStack() } },
                modifier = Modifier.fillMaxWidth(0.9f),
                enabled = !state.isSaving,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFda0c49))
            ) {
                if (state.isSaving) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                } else {
                    Text("Add Place")
                }
            }
        }
    }
}
