package com.advasoft.touchretouc.providers

import android.content.Context
import android.util.Log
import com.onesignal.OneSignal

class OneSignalProvider(context: Context) {
    init {
        OneSignal.initWithContext(context)
        OneSignal.setAppId(ID)
    }

    fun sendTag(dataFb: String, dataAF: MutableMap<String, Any>?) {
        when {
            dataAF?.get("campaign").toString() == "null" && dataFb == "null" -> {
                OneSignal.sendTag(
                    "key2",
                    "organic"
                )
            }
            dataFb != "null" && dataAF?.get("campaign").toString() == "null" -> {
                OneSignal.sendTag(
                    "key2",
                    dataFb.replace("myapp://", "").substringBefore("/")
                )
            }
            dataAF?.get("campaign").toString() != "null" && dataFb == "null" -> {
                OneSignal.sendTag(
                    "key2",
                    dataAF?.get("campaign").toString().substringBefore("_")
                )
            }
        }
    }

    companion object {
        private const val ID = "be24bcf6-3bf4-42ea-ac34-11ceac8812c5"
    }
}