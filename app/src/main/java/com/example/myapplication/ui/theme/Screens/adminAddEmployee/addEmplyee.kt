package com.example.myapplication.ui.theme.Screens.adminAddEmployee

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Screens.userRegistration.userRegistration

@Composable
fun addEmployee (navController: NavController){
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var pasword by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    Column (modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        Text( text = "ADD EMPLOYEE",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(18.dp))

        Image(painter = painterResource(R.drawable.logo),
            contentDescription = "App log",
            modifier = Modifier.size(140.dp))


        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(value = firstname,
            onValueChange = {firstname=it},
            label = {Text("Enter your firstname")},
            placeholder = {Text("Enter firstname")},
            leadingIcon = {Icon(Icons.Default.Person, contentDescription = "first name")})
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = lastname,
            onValueChange = {lastname=it},
            label = {Text("Enter your firstname")},
            placeholder = {Text("Enter firstname")},
            leadingIcon = {Icon(Icons.Default.Person, contentDescription = "Last name")})
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            label = {Text("Enter Email")},
            placeholder = {Text("Enter E-Mail")},
            leadingIcon = {Icon(Icons.Default.Email, contentDescription = "Email")})
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = gender,
            onValueChange = {gender=it},
            label = {Text("Enter your Gender")},
            placeholder = {Text("Enter Gender")})
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = pasword,
            onValueChange = {pasword=it},
            label = {Text("Enter your Password")},
            placeholder = {Text("Enter Password")},
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "password")})
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = confirmpassword,
            onValueChange = {confirmpassword=it},
            label = {Text("Confirm Password")},
            placeholder = {Text("Confirm Password")},
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "password")})
        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {}, modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray ))
        {Text(text = "Register", color= Color.White, fontSize = 25.sp) }
        Text(text = "Already registered?")
        Text(text = "Login her", modifier = Modifier.clickable{ },
            color = Color.Blue, fontWeight = FontWeight.Bold)






    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun addEmployeePreview( ){
    addEmployee(rememberNavController())

}