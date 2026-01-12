package com.example.celler_app.domain

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "celler_settings")

class AppPreferences(private val context: Context) {

    companion object {
        val MODE = stringPreferencesKey("mode")

        val FAKE_NAME = stringPreferencesKey("fake_name")
        val FAKE_NUMBER = stringPreferencesKey("fake_number")

        val EMER_CALL = stringPreferencesKey("emergency_call")
        val EMER_SMS = stringPreferencesKey("emergency_sms")
        val EMER_MESSAGE = stringPreferencesKey("emergency_message")
    }

    suspend fun setMode(mode: String) {
        context.dataStore.edit { prefs ->
            prefs[MODE] = mode
        }
    }

    fun getMode(): Flow<String> = context.dataStore.data.map { prefs ->
        prefs[MODE] ?: "FAKE_CALL"
    }

    suspend fun saveSettings(
        fakeName: String,
        fakeNumber: String,
        emerCall: String,
        emerSms: String,
        emerMessage: String
    ) {
        context.dataStore.edit { prefs ->
            prefs[FAKE_NAME] = fakeName
            prefs[FAKE_NUMBER] = fakeNumber
            prefs[EMER_CALL] = emerCall
            prefs[EMER_SMS] = emerSms
            prefs[EMER_MESSAGE] = emerMessage
        }
    }

    fun loadSettings(): Flow<SettingsData> = context.dataStore.data.map { prefs ->
        SettingsData(
            fakeName = prefs[FAKE_NAME] ?: "",
            fakeNumber = prefs[FAKE_NUMBER] ?: "",
            emergencyCall = prefs[EMER_CALL] ?: "",
            emergencySms = prefs[EMER_SMS] ?: "",
            emergencyMessage = prefs[EMER_MESSAGE] ?: "I need help!"
        )
    }
}

data class SettingsData(
    val fakeName: String,
    val fakeNumber: String,
    val emergencyCall: String,
    val emergencySms: String,
    val emergencyMessage: String
)