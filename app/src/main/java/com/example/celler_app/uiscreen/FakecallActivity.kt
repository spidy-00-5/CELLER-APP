package com.example.celler_app.uiscreen

import kotlinx.coroutines.flow.first


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.celler_app.domain.AppPreferences
import com.example.celler_app.ui.theme.CellerAppTheme
import kotlinx.coroutines.runBlocking

class FakeCallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make it appear on lock screen
        setShowWhenLocked(true)
        setTurnScreenOn(true)

        val prefs = AppPreferences(this)
        val data = runBlocking { prefs.loadSettings().first() }

        setContent {
            CellerAppTheme {
                FakeCallScreen(
                    data = data,
                    onAccept = { finish() },
                    onEnd = { finish() }
                )
            }
        }
    }
}