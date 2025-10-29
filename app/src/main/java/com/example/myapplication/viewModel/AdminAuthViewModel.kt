package com.example.myapplication.viewModel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myapplication.navigation.ROUTE_ADMIN_DASHBOARD
import com.example.myapplication.navigation.ROUTE_DASHBOARD

class AdminAuthViewModel: ViewModel() {
    fun login(email: String,password: String, navController: NavController, context: android.content.Context){
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(context,"Email and Password required", Toast.LENGTH_LONG).show()
        }
        if(email == "admin@lk.com" && password=="admin") {


                Toast.makeText(context,"Login successful", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_ADMIN_DASHBOARD){
                    popUpTo(0)
                }
            }else{
                Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show()
            }
        }// end of customer login function
    }


