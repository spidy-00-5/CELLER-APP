package com.example.celler_app.emergency

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.telephony.SmsManager
import android.content.Intent
import android.net.Uri
import com.google.android.gms.location.LocationServices

class EmergencyActionHelper(private val context: Context) {

    fun startCall(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
    fun sendSms(number: String, message: String) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(number, null, message, null, null)
    }
    @SuppressLint("MissingPermission")
    fun sendLocation(number: String, messagePrefix: String = "Location: ") {
        val fused = LocationServices.getFusedLocationProviderClient(context)

        fused.lastLocation.addOnSuccessListener { loc: Location? ->
            if (loc != null) {
                val msg = "$messagePrefix https://maps.google.com/?q=${loc.latitude},${loc.longitude}"
                sendSms(number, msg)
            }
        }
    }
}