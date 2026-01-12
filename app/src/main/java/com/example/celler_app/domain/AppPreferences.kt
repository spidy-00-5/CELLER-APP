package com.example.celler_app.domain

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("celler_settings")

class AppPreferences(private val context: Context) {

    companion object {
        val MODE_KEY = stringPreferencesKey("mode_key")
    }

    suspend fun setMode(mode: String) {
        context.dataStore.edit { prefs ->
            prefs[MODE_KEY] = mode
        }
    }

    val mode: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[MODE_KEY] ?: "FAKE_CALL" // default mode
    }
}
