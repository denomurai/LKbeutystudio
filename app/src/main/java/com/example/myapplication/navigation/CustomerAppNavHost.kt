import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.navigation.Routes
import com.example.myapplication.ui.theme.Screens.custBookEyebrows.bookingEyebrowsScreen
import com.example.myapplication.ui.theme.Screens.custBookingScreen.custBookingScreen
import com.example.myapplication.ui.theme.Screens.custViewEybrows.custViewEybrowsScreen
import com.example.myapplication.ui.theme.Screens.dashboard.dashBoardScreen
import com.example.myapplication.ui.theme.Screens.userLogin.userLogin
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun CustomerAppNavHost() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val auth = FirebaseAuth.getInstance()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
                Divider()

                TextButton (onClick = {
                    navController.navigate(Routes.EyebrowsService)
                    scope.launch { drawerState.close() }
                }) { Text("Eyebrows Service") }

                TextButton(onClick = {
                    navController.navigate(Routes.BookingHistory)
                    scope.launch { drawerState.close() }
                }) { Text("Booking History") }

                Divider()

                TextButton(onClick = {
                    auth.signOut()
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Dashboard) { inclusive = true }
                    }
                    scope.launch { drawerState.close() }
                }) { Text("Log Out") }
            }
        }
    ) {
        // NavHost *MUST* be here inside ModalNavigationDrawer
        NavHost(
            navController = navController,
            startDestination = Routes.Login
        ) {
            // ✅ DASHBOARD (Drawer works here)
            composable(Routes.Dashboard) {
                dashBoardScreen(
                    navController = navController,
                    openDrawer = { scope.launch { drawerState.open() } }
                )
            }

            composable(Routes.EyebrowsService) { custViewEybrowsScreen(navController) }
            composable(Routes.BookingHistory) { custBookingScreen(navController) }
            composable(Routes.Login) { userLogin(navController) }

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
}
