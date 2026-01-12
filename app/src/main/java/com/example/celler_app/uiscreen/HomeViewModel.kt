package com.example.celler_app.uiscreen


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.celler_app.domain.AppPreferences
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    private val prefs = AppPreferences(app)

    fun setFakeCallMode() {
        viewModelScope.launch {
            prefs.setMode("FAKE_CALL")
        }
    }

    fun setEmergencyMode() {
        viewModelScope.launch {
            prefs.setMode("EMERGENCY")
        }
    }
}