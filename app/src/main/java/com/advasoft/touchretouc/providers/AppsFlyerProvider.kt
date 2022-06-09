package com.advasoft.touchretouc.providers

import android.content.Context
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import kotlin.coroutines.suspendCoroutine

class AppsFlyerProvider(private val context: Context) {
    var appsUID: String? = null

    suspend fun instance(): MutableMap<String, Any>? = suspendCoroutine { completion ->
        val appsFlyerCallback = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                completion.resumeWith(Result.success(data))
            }

            override fun onConversionDataFail(p0: String?) {}
            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
            override fun onAttributionFailure(p0: String?) {}

        }
        AppsFlyerLib.getInstance().init(ID, appsFlyerCallback, context)
        AppsFlyerLib.getInstance().start(context)
        appsUID = AppsFlyerLib.getInstance().getAppsFlyerUID(context)
    }

    companion object {
        private const val ID = "bZLLY9o3qmEzizQWixgUSm"
    }
}