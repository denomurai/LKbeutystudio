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
import com.example.myapplication.ui.theme.Screens.adminApproveEybrows.adminViewBookingsScreen
import com.example.myapplication.ui.theme.Screens.adminDashboard.adminDashboard
import com.example.myapplication.ui.theme.Screens.adminUpdateEyebrows.UpdateEyebrows
import com.example.myapplication.ui.theme.Screens.adminlogin.adminLoginScreen
import com.example.myapplication.ui.theme.Screens.custBookEyebrows.bookingEyebrowsScreen
import com.example.myapplication.ui.theme.Screens.custBookingScreen.custBookingScreen
import com.example.myapplication.ui.theme.Screens.custViewEybrows.custViewEybrowsScreen
import com.example.myapplication.ui.theme.Screens.dashboard.dashBoardScreen
import com.example.myapplication.ui.theme.Screens.userLogin.userLogin
import com.example.myapplication.ui.theme.Screens.userRegistration.userRegistration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_USER_LOGIN,
    openDrawer: (() -> Unit)? = null,
    scope: CoroutineScope? = null
) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {

            // -------------------- USER ROUTES -------------------- //
            composable(ROUTE_USER_REGISTER) {
                userRegistration(navController)
            }

            composable(ROUTE_USER_LOGIN) {
                userLogin(navController)
            }

            composable(ROUTE_DASHBOARD) {
                dashBoardScreen(
                    navController = navController,
                    openDrawer = openDrawer
                )
            }

            composable(ROUTE_CUST_VEIW_EYEBROS) {
                custViewEybrowsScreen(navController)
            }

            // Book service with arguments
            composable(
                route = "bookService/{name}/{description}/{amount}",
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType },
                    navArgument("amount") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val description = backStackEntry.arguments?.getString("description") ?: ""
                val amount = backStackEntry.arguments?.getString("amount") ?: ""
                bookingEyebrowsScreen(navController, name, description, amount)
            }

            // -------------------- ADMIN ROUTES -------------------- //
            composable(ROUTE_ADMIN_LOGIN) {
                adminLoginScreen(navController)
            }

            composable(ROUTE_ADMIN_DASHBOARD) {
                adminDashboard(navController)
            }

            composable(ROUTE_ADD_EMP) {
                addEmployee(navController)
            }

            composable(ROUTE_ADD_Eyebrows) {
                addEyebrowsScreen(navController)
            }

            composable(ROUTE_ADD_LASHES) {
                addLashesScreen(navController)
            }

            composable(ROUTE_ADD_Lip) {
                addLipScreen(navController)
            }

            composable(ROUTE_ADD_Others) {
                addOtherServicesScreeen(navController)
            }

            composable(ROUTE_ADMIN_VIEW_EYEBROWS) {
                adminViewBookingsScreen(navController)
            }

            composable(
                route = "$ROUTE_ADMIN_UPDATE_Eyebrows/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: ""
                UpdateEyebrows(navController, id)
            }

            // -------------------- DRAWER ROUTES -------------------- //
            // ✅ DASHBOARD (Drawer works here)
            composable(Routes.Dashboard) {
                dashBoardScreen(
                    navController = navController,
                    openDrawer = { scope?.launch { openDrawer?.invoke() } }
                )
            }

            composable(Routes.EyebrowsService) {
                custViewEybrowsScreen(navController)
            }

            composable(Routes.BookingHistory) {
                custBookingScreen(navController)
            }

            composable(Routes.Login) {
                userLogin(navController)
            }

            composable(Routes.UserRegister) {
                userRegistration(navController)
            }

           /* composable(ROUTE_ADMIN_LOGIN) {
                adminLoginScreen(navController)
            }*/

            composable(
                route = Routes.BookingScreen,
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("desc") { type = NavType.StringType },
                    navArgument("amount") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val desc = backStackEntry.arguments?.getString("desc") ?: ""
                val amount = backStackEntry.arguments?.getString("amount") ?: ""
                bookingEyebrowsScreen(navController, name, desc, amount)
            }
        }
    }

