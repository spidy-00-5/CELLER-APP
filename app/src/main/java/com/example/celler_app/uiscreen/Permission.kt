package com.example.celler_app.uiscreen

import android.R.attr.text
import android.R.attr.top
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.celler_app.R
import com.example.celler_app.ui.theme.CellerAppTheme
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun PermissionScreen(
    modifier: Modifier = Modifier,
    onPermissionGranted: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center
    ){

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
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

