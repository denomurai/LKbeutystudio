package com.example.myapplication.ui.theme.Screens.adminAddLashes

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Screens.adminAddLip.addLipScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addLashesScreen(navController: NavController){
    val imageUri = rememberSaveable { mutableStateOf <Uri?> (null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> uri?.let {imageUri.value=it}}

    var name by remember {mutableStateOf("")}
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Image(painter = painterResource(id = R.drawable.logo),
                        contentDescription = "App log",
                        modifier = Modifier.size(140.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    //Text("LK Beauty Studio", fontSize = 24.sp)
                },

                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = +0xff28a9b5), titleContentColor = Color.White)
                ,
                actions = {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Log out", modifier = Modifier.size(40.dp), Color.White)
                })

        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.width(28.dp))
            Text("Add New Lashes Service", fontSize = 24.sp, color = Color(color = 0xFF004D40))
            Spacer(modifier = Modifier.height(6.dp))

            Card(
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier.size(140.dp).clickable { launcher.launch("image/*") }) {
                /*AnimatedContent(targetState = imageUri.value, label="") {
                        targetUri ->
                    AsyncImage(
                        model = targetUri ?: R.drawable.ic_person,
                        contentDescription  ="Employee Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }*/
            }
            Spacer(modifier = Modifier.height(13.dp))

            Text("Tap to upload picture", color = Color.Black)
            Divider(
                modifier = Modifier.padding(vertical = 5.dp),
                color = Color.Black,
                thickness = 1.dp
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter your Name") },
                //placeholder = {Text("Enter  Name")},
                modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                shape = RoundedCornerShape(16.dp),


                )
            Spacer(modifier = Modifier.height(12.dp))


            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Enter Cost") },
                placeholder = { Text("Enter Cost") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),


                )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Enter your summary") },
                placeholder = { Text("Enter summary") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp).height(100.dp),
                shape = RoundedCornerShape(16.dp),
                maxLines = 5,
                leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                )
                { Text("CANCEL") }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                )
                { Text("SAVE") }
            }
        }

    }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun addLashesPreview( ){
        addLashesScreen(rememberNavController())

    }

