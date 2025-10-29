package com.example.myapplication.ui.theme.Screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.navigation.ROUTE_ADMIN_LOGIN
import com.example.myapplication.navigation.ROUTE_USER_LOGIN
import com.example.myapplication.ui.theme.Screens.userRegistration.userRegistration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dashBoardScreen (navController: NavController){
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

    },
        bottomBar = {
            NavigationBar(containerColor = Color(color = +0xff28a9b5), )
            {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "\u00A9 Lk Beauty Studio 2025", modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End){

                        TextButton(onClick = {navController.navigate(ROUTE_ADMIN_LOGIN)}) { Text("Admin Login", modifier = Modifier) }
                    }
                }
            }
        }
    ){innerPadding ->
        Column (modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("OUR SERCICES", modifier = Modifier, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(28.dp))

            Card (modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable{navController.navigate( ROUTE_USER_LOGIN )},
                elevation = CardDefaults.cardElevation(10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(color = 0xFFB519A0))
            ){

                Column  (modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    Icon(Icons.Default.Person, contentDescription = "Add Employee", modifier = Modifier.size(40.dp), Color(0xFF004D40))
                    Spacer(modifier = Modifier.width(20.dp))

                        Text("EYEBROWS SERVICES",fontSize = 20.sp, color = Color.White)

                }
            }// End of Eyebrows

            Card (modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(color = 0xFFB519A0))
            ){

                Column  (modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    Icon(Icons.Default.Person, contentDescription = "Add Employee", modifier = Modifier.size(40.dp), Color(0xFF004D40))
                    Spacer(modifier = Modifier.width(20.dp))

                    Text("LIPS SERVICES",fontSize = 20.sp, color = Color.White)

                }
            }//End of Lips services

            Card (modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(color = 0xFFB519A0))
            ){

                Column  (modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    Icon(Icons.Default.Person, contentDescription = "Add Employee", modifier = Modifier.size(40.dp), Color(0xFF004D40))
                    Spacer(modifier = Modifier.width(20.dp))

                    Text("EYE LASHES SERVICES",fontSize = 20.sp, color = Color.White)

                }
            }// End of eye lashes

            Card (modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(color = 0xFFB519A0))
            ){

                Column  (modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    Icon(Icons.Default.Person, contentDescription = "Add Employee", modifier = Modifier.size(40.dp), Color(0xFF004D40))
                    Spacer(modifier = Modifier.width(20.dp))

                    Text("OTHER SERVICES",fontSize = 20.sp, color = Color.White)

                }
            }//Other Services









            }





        }





}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun dashBoardPreview( ){
    dashBoardScreen(rememberNavController())

}