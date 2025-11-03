package com.example.myapplication.ui.theme.Screens.adminApproveEybrows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.model.EyebrowsBooking
import com.example.myapplication.viewModel.AdminBookingViewModel

import androidx.compose.foundation.lazy.items


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun adminViewBookingsScreen(navController: NavController) {
    val adminViewModel: AdminBookingViewModel = viewModel ()
    val context = LocalContext.current
    val bookings = adminViewModel.bookings

    LaunchedEffect(Unit) {
        adminViewModel.fetchAllBookings(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Eyebrow Bookings") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFB519A0), titleContentColor = Color.White)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF4F4F4)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(bookings) { booking ->
                BookingCard(booking = booking, onStatusChange = { status ->
                    adminViewModel.updateBookingStatus(booking.bookingId, status)
                })
            }
        }
    }
}

@Composable
fun BookingCard(booking: EyebrowsBooking, onStatusChange: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Customer ID: ${booking.userId}")
            Text("Service: ${booking.userName}")
            Text("Service: ${booking.serviceName}")
            Text("Amount: ${booking.amount}")
            Text("Date: ${booking.date}")
            Text("Status: ${booking.status}", color = when (booking.status) {
                "Approved" -> Color(0xFF2E7D32)
                "Declined" -> Color(0xFFC62828)
                else -> Color(0xFF757575)
            })

            Spacer(modifier = Modifier.height(12.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button (
                    onClick = { onStatusChange("Approved") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                ) {
                    Text("Approve")
                }
                Button(
                    onClick = { onStatusChange("Declined") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828))
                ) {
                    Text("Decline")
                }
            }
        }
    }
}