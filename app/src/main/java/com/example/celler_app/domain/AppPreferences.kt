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
        // Fake Call
        val FAKE_TRIGGER_COUNT = intPreferencesKey("fake_trigger_count")
        val FAKE_CALLER_NAME = stringPreferencesKey("fake_caller_name")
        val FAKE_CALLER_NUMBER = stringPreferencesKey("fake_caller_number")
        val FAKE_PHOTO_URI = stringPreferencesKey("fake_photo_uri")
        val FAKE_THEME = stringPreferencesKey("fake_theme")
        val FAKE_DELAY = intPreferencesKey("fake_delay")

        // Emergency
        val EMERGENCY_TRIGGER_COUNT = intPreferencesKey("emergency_trigger_count")
        val EMERGENCY_CALL_NUMBER = stringPreferencesKey("emergency_call_number")
        val EMERGENCY_SMS_NUMBER = stringPreferencesKey("emergency_sms_number")
        val EMERGENCY_MESSAGE = stringPreferencesKey("emergency_message")
        val EMERGENCY_SHARE_LOCATION = booleanPreferencesKey("emergency_share_location")
        val EMERGENCY_AUTO_CALL = booleanPreferencesKey("emergency_auto_call")
    }

    // --- Save Methods ---
    suspend fun saveFakeCallSettings(
        trigger: Int,
        name: String,
        number: String,
        photo: String,
        theme: String,
        delay: Int
    ) {
        context.dataStore.edit { prefs ->
            prefs[FAKE_TRIGGER_COUNT] = trigger
            prefs[FAKE_CALLER_NAME] = name
            prefs[FAKE_CALLER_NUMBER] = number
            prefs[FAKE_PHOTO_URI] = photo
            prefs[FAKE_THEME] = theme
            prefs[FAKE_DELAY] = delay
        }
    }

    suspend fun saveEmergencySettings(
        trigger: Int,
        callNumber: String,
        smsNumber: String,
        message: String,
        share: Boolean,
        autoCall: Boolean
    ) {
        context.dataStore.edit { prefs ->
            prefs[EMERGENCY_TRIGGER_COUNT] = trigger
            prefs[EMERGENCY_CALL_NUMBER] = callNumber
            prefs[EMERGENCY_SMS_NUMBER] = smsNumber
            prefs[EMERGENCY_MESSAGE] = message
            prefs[EMERGENCY_SHARE_LOCATION] = share
            prefs[EMERGENCY_AUTO_CALL] = autoCall
        }
    }

    // --- Read ---
    fun getSettings(): Flow<SettingsData> = context.dataStore.data.map { prefs ->
        SettingsData(
            fakeTrigger = prefs[FAKE_TRIGGER_COUNT] ?: 3,
            fakeCallerName = prefs[FAKE_CALLER_NAME] ?: "",
            fakeCallerNumber = prefs[FAKE_CALLER_NUMBER] ?: "",
            fakePhotoUri = prefs[FAKE_PHOTO_URI] ?: "",
            fakeTheme = prefs[FAKE_THEME] ?: "DEFAULT",
            fakeDelay = prefs[FAKE_DELAY] ?: 0,

            emergencyTrigger = prefs[EMERGENCY_TRIGGER_COUNT] ?: 3,
            emergencyCallNumber = prefs[EMERGENCY_CALL_NUMBER] ?: "",
            emergencySmsNumber = prefs[EMERGENCY_SMS_NUMBER] ?: "",
            emergencyMessage = prefs[EMERGENCY_MESSAGE] ?: "I need help!",
            emergencyShareLocation = prefs[EMERGENCY_SHARE_LOCATION] ?: false,
            emergencyAutoCall = prefs[EMERGENCY_AUTO_CALL] ?: false,
        )
    }
}

data class SettingsData(
    val fakeTrigger: Int,
    val fakeCallerName: String,
    val fakeCallerNumber: String,
    val fakePhotoUri: String,
    val fakeTheme: String,
    val fakeDelay: Int,
    val emergencyTrigger: Int,
    val emergencyCallNumber: String,
    val emergencySmsNumber: String,
    val emergencyMessage: String,
    val emergencyShareLocation: Boolean,
    val emergencyAutoCall: Boolean
)