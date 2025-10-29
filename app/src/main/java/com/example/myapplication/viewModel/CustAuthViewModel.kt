package com.example.myapplication.viewModel

import android.widget.Toast
import androidx.compose.ui.layout.FirstBaseline
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myapplication.model.customerModel
import com.example.myapplication.navigation.ROUTE_DASHBOARD
import com.example.myapplication.navigation.ROUTE_USER_LOGIN
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CustAuthViewModel: ViewModel() {
    private val custauth: FirebaseAuth= FirebaseAuth.getInstance()
    fun signup(firstname: String, lastname: String, email: String,gender:String, password: String, confirmpassword: String, navController: NavController, context: android.content.Context){
        if (firstname.isBlank() || lastname.isBlank() || email.isBlank()||gender.isBlank() || password.isBlank() || confirmpassword.isBlank()){
            Toast.makeText(context,"Please fill all the fields", Toast.LENGTH_LONG).show()
            return
        }
        if(password != confirmpassword){
            Toast.makeText(context,"Password do not match", Toast.LENGTH_LONG).show()
        }
        custauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                val userId = custauth.currentUser?.uid?:""
                val customer = customerModel( firstname = firstname, lastname = lastname, email = email,gender=gender, userId = userId)

                saveUserToDatabase(customer,navController,context)
            } else{
                Toast.makeText(context,"Registration failed", Toast.LENGTH_LONG).show()
            }
        }

    }//end of signup function

    private fun saveUserToDatabase(customer: customerModel, navController: NavController,context: android.content.Context){
        val dbRef = FirebaseDatabase.getInstance().getReference("Customer/${customer.userId}")
        dbRef.setValue(customer).addOnCompleteListener {
                task ->
            if (task.isSuccessful){
                Toast.makeText(context,"User Registered Successful", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_USER_LOGIN){
                    popUpTo(0)
                }
            }else{
                Toast.makeText(context,"Failed to save User", Toast.LENGTH_LONG).show()
            }
        }

    }//end of save user to DB function

    fun login(email: String,password: String, navController: NavController, context: android.content.Context){
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(context,"Email and Password required", Toast.LENGTH_LONG).show()
        }
        custauth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                Toast.makeText(context,"Login successful", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_DASHBOARD){
                    popUpTo(0)
                }
            }else{
                Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show()
            }
        }
    }// end of customer login function

}