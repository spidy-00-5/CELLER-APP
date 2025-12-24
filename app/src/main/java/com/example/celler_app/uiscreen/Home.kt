package com.example.celler_app.uiscreen

import android.util.Log.i
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.celler_app.R
import com.example.celler_app.ui.theme.GreenPrimary




@Composable
fun HomeScreen(
    modifier: Modifier,
    onFakeCall: () -> Unit,
    onEmergency: () -> Unit,
){
    Column(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.padding(10.dp),
            text = stringResource(R.string.greeting),
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Black)
        )
        TextButton(
            onClick = onFakeCall,
            modifier = Modifier.padding(10.dp),
            shape = RoundedCornerShape(16.dp),
        ){
            Text(
                text = stringResource(R.string.fakecall),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen( modifier = Modifier, onFakeCall = {}, onEmergency = {})
    
}
