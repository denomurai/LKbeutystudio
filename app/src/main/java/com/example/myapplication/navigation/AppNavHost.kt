package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.Screens.adminAddEmployee.addEmployee
import com.example.myapplication.ui.theme.Screens.adminAddEyebrows.addEyebrowsScreen
import com.example.myapplication.ui.theme.Screens.adminAddLashes.addLashesScreen
import com.example.myapplication.ui.theme.Screens.adminAddLip.addLipScreen
import com.example.myapplication.ui.theme.Screens.adminAddOthers.addOtherServicesScreeen
import com.example.myapplication.ui.theme.Screens.adminDashboard.adminDashboard
import com.example.myapplication.ui.theme.Screens.adminlogin.adminLoginScreen
import com.example.myapplication.ui.theme.Screens.dashboard.dashBoardScreen
import com.example.myapplication.ui.theme.Screens.userLogin.userLogin
import com.example.myapplication.ui.theme.Screens.userRegistration.userRegistration

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination: String = ROUTE_DASHBOARD){
    NavHost(navController = navController, startDestination = startDestination){

        composable (ROUTE_USER_REGISTER){ userRegistration(navController) }
        composable (ROUTE_USER_LOGIN){ userLogin(navController) }
        composable (ROUTE_DASHBOARD){ dashBoardScreen(navController) }
        composable (ROUTE_ADMIN_LOGIN){ adminLoginScreen(navController) }

        //Admin ROUTES
        composable (ROUTE_ADD_EMP){ addEmployee(navController) }
        composable (ROUTE_ADD_Eyebrows){ addEyebrowsScreen(navController) }
        composable (ROUTE_ADD_LASHES){ addLashesScreen(navController) }
        composable (ROUTE_ADD_Lip){ addLipScreen(navController) }
        composable (ROUTE_ADD_Others){ addOtherServicesScreeen(navController) }
        composable (ROUTE_ADMIN_DASHBOARD){ adminDashboard(navController) }




    }}