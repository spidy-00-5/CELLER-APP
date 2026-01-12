package com.example.celler_app.uiscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.celler_app.domain.SettingsData

@Composable
fun FakeCallScreen(
    data: SettingsData,
    onEnd: () -> Unit,
    onAccept: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Incoming call...",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                text = data.fakeName.ifBlank { "Unknown" },
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = data.fakeNumber.ifBlank { "No Number" },
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = onAccept,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text("Accept")
            }

            Button(
                onClick = onEnd,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F)
                )
            ) {
                Text("Decline")
            }
        }
    }
}