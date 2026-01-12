package com.example.celler_app.uiscreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = viewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 4.dp, top = 10.dp)
            )
        }

        // Fake Call Section Title
        Text(
            text = "Fake Call",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(top = 28.dp, bottom = 6.dp)
        )
        Divider()

        // Caller Name
        TextField(
            value = state.fakeName,
            onValueChange = { viewModel.updateFakeName(it) },
            label = { Text("Caller Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
        )

        // Caller Number
        TextField(
            value = state.fakeNumber,
            onValueChange = { viewModel.updateFakeNumber(it) },
            label = { Text("Caller Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
        )

        // Emergency Section Title
        Text(
            text = "Emergency",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(top = 32.dp, bottom = 6.dp)
        )
        Divider()

        // Emergency Call
        TextField(
            value = state.emergencyCall,
            onValueChange = { viewModel.updateEmerCall(it) },
            label = { Text("Emergency Call Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
        )

        // Emergency SMS Number
        TextField(
            value = state.emergencySms,
            onValueChange = { viewModel.updateEmerSms(it) },
            label = { Text("Emergency SMS Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
        )

        // Emergency Message Field
        TextField(
            value = state.emergencyMessage,
            onValueChange = { viewModel.updateEmerMessage(it) },
            label = { Text("Emergency Message") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
        )

        // Save Button
        Button(
            onClick = { viewModel.save() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text("Save")
        }
    }
}