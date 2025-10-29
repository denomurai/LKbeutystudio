package com.example.myapplication.ui.theme.Screens.adminUpdateEyebrows

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.model.EyebrowsModel
import com.example.myapplication.viewModel.productViewmodel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

@Composable
fun UpdateEyebrows(navController: NavController, id: String) {
    val productViewmodel: productViewmodel = viewModel()
    var eyebrows by remember { mutableStateOf<EyebrowsModel?>(null) }

    // Fetch the eyebrow data
    LaunchedEffect(id) {
        val ref = FirebaseDatabase.getInstance()
            .getReference("eyebrowServices")
            .child(id)
        val snapshot = ref.get().await()
        eyebrows = snapshot.getValue(EyebrowsModel::class.java)?.copy(id = id)
    }

    if (eyebrows == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    var name by remember { mutableStateOf(eyebrows!!.name) }
    var amount by remember { mutableStateOf(eyebrows!!.amount.toString()) }
    var description by remember { mutableStateOf(eyebrows!!.description) }

    val imageUrl = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri -> imageUrl.value = uri }
    }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFCE4EC), Color(0xFFF8BBD0))))
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Update Eyebrow Service",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF880E4F)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier
                        .size(140.dp)
                        .clickable { launcher.launch("image/*") }
                        .shadow(8.dp, CircleShape)
                ) {
                    AnimatedContent(targetState = imageUrl.value, label = "Image Picker Animation") {
                        AsyncImage(
                            model = it ?: eyebrows!!.imageUrl,
                            contentDescription = "Eyebrow Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Text(
                    text = "Tap to change picture",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Divider(
                    modifier = Modifier.padding(vertical = 20.dp),
                    color = Color.LightGray,
                    thickness = 1.dp
                )

                val fieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Service Name") },
                    placeholder = { Text("e.g., Microblading") },
                    modifier = fieldModifier,
                    shape = RoundedCornerShape(14.dp)
                )

                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Cost of Service") },
                    placeholder = { Text("e.g., 5000") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = fieldModifier,
                    shape = RoundedCornerShape(14.dp)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    placeholder = { Text("Enter service details") },
                    modifier = fieldModifier,
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text("Go Back", color = Color.DarkGray)
                    }

                    Button(
                        onClick = {
                            productViewmodel.updateEyebrows(
                                id,
                                imageUrl.value,
                                name,
                                amount.toDoubleOrNull() ?: 0.0,
                                description,
                                context
                            )
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text("Update", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
