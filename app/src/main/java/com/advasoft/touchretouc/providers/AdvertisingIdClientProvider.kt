package com.advasoft.touchretouc.providers

import android.content.Context
import androidx.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdvertisingIdClientProvider(private val context: Context) {
    suspend fun getAdId(): String = withContext(Dispatchers.Default) {
        AdvertisingIdClient.getAdvertisingIdInfo(context).get().id
    }
}