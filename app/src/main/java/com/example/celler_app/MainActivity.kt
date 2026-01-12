package com.example.celler_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.celler_app.navigation.Routes
import com.example.celler_app.ui.theme.CellerAppTheme
import com.example.celler_app.uiscreen.SettingsScreen
import com.example.celler_app.ui.home.HomeScreen
import com.example.celler_app.ui.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    private val requiredPermissions = arrayOf(
        android.Manifest.permission.SEND_SMS,
        android.Manifest.permission.CALL_PHONE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    private fun requestAppPermissions() {
        requestPermissions(requiredPermissions, 2001)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CellerAppTheme {
                val navController = rememberNavController()
                val vm: HomeViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME
                ) {

                    composable(Routes.HOME) {
                        HomeScreen(
                            onFakeCall = {
                                vm.setFakeCallMode()
                                navController.navigate(Routes.SETTINGS)
                            },
                            onEmergency = {
                                vm.setEmergencyMode()
                                navController.navigate(Routes.SETTINGS)
                            }
                        )
                    }

                    composable(Routes.SETTINGS) {
                        SettingsScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}