package com.example.celler_app.services

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.KeyEvent
import kotlinx.coroutines.*
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import com.example.celler_app.domain.AppPreferences
import com.example.celler_app.uiscreen.FakeCallActivity

class VolumeService : AccessibilityService() {

    private var pressCount = 0
    private var lastPressTime = 0L

    override fun onAccessibilityEvent(event: android.view.accessibility.AccessibilityEvent?) {}

    override fun onInterrupt() {}

    override fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN &&
            (event.keyCode == KeyEvent.KEYCODE_VOLUME_UP || event.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {

            handleVolumePress()
            return true
        }
        return super.onKeyEvent(event)
    }

    private fun handleVolumePress() {
        val now = System.currentTimeMillis()

        if (now - lastPressTime > 2000) {
            pressCount = 0
        }

        pressCount++
        lastPressTime = now

        if (pressCount == 3) {
            pressCount = 0
            triggerAction()
        }
    }

    private fun triggerAction() {
        CoroutineScope(Dispatchers.Main).launch {
            val prefs = AppPreferences(this@VolumeService)
            val mode = prefs.getMode().first()

            when (mode) {
                "FAKE_CALL" -> handleFakeCall()
                "EMERGENCY" -> handleEmergency()
            }
        }
    }

    private fun handleFakeCall() {
        val intent = Intent(this, FakeCallActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    private fun handleEmergency() {
        val prefs = AppPreferences(this)
        CoroutineScope(Dispatchers.Main).launch {
            prefs.loadSettings().collect { data ->
                val emergencyHelper = EmergencyActionHelper(this@VolumeService)

                // 1. Send Emergency Message
                if (data.emergencySms.isNotEmpty()) {
                    emergencyHelper.sendSms(data.emergencySms, data.emergencyMessage)
                }

                // 2. Send Location (optional)
                emergencyHelper.sendLocation(data.emergencySms)

                // 3. Call Emergency Number
                if (data.emergencyCall.isNotEmpty()) {
                    emergencyHelper.startCall(data.emergencyCall)
                }
            }
        }
    }
}