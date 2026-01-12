package com.example.celler_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.celler_app.ui.theme.CellerAppTheme
import com.example.celler_app.uiscreen.HomeScreen
import com.example.celler_app.uiscreen.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CellerAppTheme() {
                    val homeViewModel: HomeViewModel = viewModel()

                    HomeScreen(
                        onFakeCall = {
                            homeViewModel.setFakeCallMode()
                            // navigate or show toast if needed
                        },
                        onEmergency = {
                            homeViewModel.setEmergencyMode()
                            // navigate or show toast if needed
                        }
                    )
                }
            }
        }
    }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

