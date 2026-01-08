package com.example.celler_app.uiscreen

import android.Manifest
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.celler_app.R
import com.example.celler_app.ui.theme.CellerAppTheme
import android.provider.Settings

@Composable
fun PermissionScreen(
    modifier: Modifier = Modifier,
    onPermissionGranted: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 15.dp),
                text = stringResource(R.string.permission_title),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center

            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp,bottom = 25.dp),
                text = stringResource(R.string.permission_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            PermissionItem(stringResource(R.string.permission_phone))
            PermissionItem(stringResource(R.string.permission_sms))
            PermissionItem(stringResource(R.string.permission_location))

            PermissionButton(onPermissionGranted)

        }
    }
}
@Composable
private fun PermissionItem(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
        )
    }
}
@Composable
fun PermissionButton(
    onPermissionGranted: () -> Unit
) {
    var showError by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            onPermissionGranted()
        } else {
            showError = true
        }
    }
    Column {
        if (showError) {
            Text(
                text = stringResource(R.string.permission_denied),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Button(
            onClick = {
                launcher.launch(
                    arrayOf(
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            },
            modifier = Modifier
                .padding(top = 60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(stringResource(R.string.permission_allow))
        }
    }
}

@Composable
fun InformationBox(
   onNext: () -> Unit
){  Column(
    modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(R.string.info_volume),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            val steps = stringArrayResource(R.array.accessibility_steps_array)
            Column(Modifier.padding(16.dp)) {
                  Text(
                       modifier = Modifier.padding(bottom = 16.dp),
                       text = stringResource(R.string.instructions),
                       style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                )
                steps.forEachIndexed { index, step ->
                    Text(
                        text = "${index + 1}. $step",
                        modifier = Modifier.padding(bottom = 8.dp),
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.Black, fontWeight = Bold),
                    )
                }
            }
        }
        Button(
            onClick = onNext,
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ){
            Text(
                text = "Next",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        OpenAccessibilitySettingsButton()
     }
  }
}
@Composable
fun OpenAccessibilitySettingsButton() {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        onClick = {
        context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
    }
    ) {
        Text(
            text = "Open Accessibility Settings",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PermissionScreenPreview() {
    CellerAppTheme(
        darkTheme = false,
        dynamicColor = false
    ){
        PermissionScreen(onPermissionGranted = {})
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InformationBoxPreview() {
    CellerAppTheme(
        darkTheme = false,
        dynamicColor = false
    ){
        InformationBox(onNext = {})

    }
}



