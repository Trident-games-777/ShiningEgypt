package com.advasoft.touchretouc.providers

import android.content.Context
import com.facebook.applinks.AppLinkData
import kotlin.coroutines.suspendCoroutine

class DeepLinkProvider(private val context: Context) {
    suspend fun instance(): String = suspendCoroutine { completion ->
        AppLinkData.fetchDeferredAppLinkData(context) {
            completion.resumeWith(Result.success(it?.targetUri.toString()))
        }
    }
}