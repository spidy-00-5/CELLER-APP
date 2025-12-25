package com.example.celler_app.uiscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.celler_app.R
import com.example.celler_app.ui.theme.CellerAppTheme

//this page contains ui of home screen of the app ,
// it has two buttons one for fake call and other for emergency mode
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onFakeCall: () -> Unit,
    onEmergency: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = stringResource(R.string.greeting),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(
                onClick = onFakeCall,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(R.string.fakecall),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground

                )
            }
            Text(
                modifier = Modifier.padding(top = 5.dp, bottom = 24.dp),
                text = stringResource(R.string.fakecall_desc),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Button(
                onClick = onEmergency,
                modifier = Modifier
                    .fillMaxWidth(),

                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(R.string.emergency),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = stringResource(R.string.emergency_desc),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    CellerAppTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        HomeScreen(
            onFakeCall = {},
            onEmergency = {}
        )
    }
}