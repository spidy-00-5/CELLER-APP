package com.example.celler_app.uiscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.celler_app.domain.AppPreferences
import com.example.celler_app.domain.SettingsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = AppPreferences(app)

    private val _uiState = MutableStateFlow(
        SettingsData("", "", "", "", "I need help!")
    )
    val uiState: StateFlow<SettingsData> = _uiState

    init {
        viewModelScope.launch {
            prefs.loadSettings().collectLatest {
                _uiState.value = it
            }
        }
    }

    fun updateFakeName(v: String) { _uiState.value = _uiState.value.copy(fakeName = v) }
    fun updateFakeNumber(v: String) { _uiState.value = _uiState.value.copy(fakeNumber = v) }
    fun updateEmerCall(v: String) { _uiState.value = _uiState.value.copy(emergencyCall = v) }
    fun updateEmerSms(v: String) { _uiState.value = _uiState.value.copy(emergencySms = v) }
    fun updateEmerMessage(v: String) { _uiState.value = _uiState.value.copy(emergencyMessage = v) }

    fun save() {
        viewModelScope.launch {
            val s = _uiState.value
            prefs.saveSettings(
                fakeName = s.fakeName,
                fakeNumber = s.fakeNumber,
                emerCall = s.emergencyCall,
                emerSms = s.emergencySms,
                emerMessage = s.emergencyMessage
            )
        }
    }
}