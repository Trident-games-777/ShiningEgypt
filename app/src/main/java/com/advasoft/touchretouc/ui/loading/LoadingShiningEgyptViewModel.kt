package com.advasoft.touchretouc.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advasoft.touchretouc.data.repo.ShiningEgyptProtoRepository
import com.advasoft.touchretouc.providers.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingShiningEgyptViewModel @Inject constructor(
    private val deepLinkProvider: DeepLinkProvider,
    private val appsFlyerProvider: AppsFlyerProvider,
    private val oneSignalProvider: OneSignalProvider,
    private val dataToUrlParser: DataToUrlParser,
    private val advertisingIdClientProvider: AdvertisingIdClientProvider,
    private val shiningEgyptProtoRepository: ShiningEgyptProtoRepository
) : ViewModel() {

    private val _isFirst: MutableLiveData<Boolean> = MutableLiveData()
    val isFirst: LiveData<Boolean> = _isFirst

    private var appsFlyer: MutableMap<String, Any>? = null
    private var deepLink: String? = null

    init {
        viewModelScope.launch {
            appsFlyer = initAppsFlyerAsync().await()
            deepLink = initDeepLinkAsync().await()
            _isFirst.postValue(appsFlyer?.get("is_first_launch") as Boolean)
        }
    }

    private fun initAppsFlyerAsync() = viewModelScope.async {
        appsFlyerProvider.instance()
    }

    private fun initDeepLinkAsync() = viewModelScope.async {
        deepLinkProvider.instance()
    }

    suspend fun getCurrentUrl(): String = shiningEgyptProtoRepository.getCurrentUrl()

    suspend fun createUrl(): String {
        val url = dataToUrlParser.parse(
            dataFb = deepLink.toString(),
            dataAF = appsFlyer,
            gadid = advertisingIdClientProvider.getAdId(),
            appsUID = appsFlyerProvider.appsUID!!
        )
        coroutineScope { launch { oneSignalProvider.sendTag(deepLink.toString(), appsFlyer) } }
        shiningEgyptProtoRepository.updateUrl(url)
        return url
    }
}