package com.advasoft.touchretouc.providers

import androidx.core.net.toUri
import java.util.*

class DataToUrlParser() {
    fun parse(dataFb: String, dataAF: MutableMap<String, Any>?, gadid: String, appsUID: String) =
        BASE_LINK.toUri().buildUpon().apply {
            appendQueryParameter(SECURE_GET_PARAMETER, SECURE_KEY)
            appendQueryParameter(DEV_TMZ_KEY, TimeZone.getDefault().id)
            appendQueryParameter(GADID_KEY, gadid)
            appendQueryParameter(DEEPLINK_KEY, dataFb)
            appendQueryParameter(SOURCE_KEY, dataAF?.get("media_source").toString())
            appendQueryParameter(AF_ID_KEY, appsUID)
            appendQueryParameter(ADSET_ID_KEY, dataAF?.get("adset_id").toString())
            appendQueryParameter(CAMPAIGN_ID_KEY, dataAF?.get("campaign_id").toString())
            appendQueryParameter(APP_CAMPAIGN_KEY, dataAF?.get("campaign").toString())
            appendQueryParameter(ADSET_KEY, dataAF?.get("adset").toString())
            appendQueryParameter(ADGROUP_KEY, dataAF?.get("adgroup").toString())
            appendQueryParameter(ORIG_COST_KEY, dataAF?.get("orig_cost").toString())
            appendQueryParameter(AF_SITEID_KEY, dataAF?.get("af_siteid").toString())
        }.toString()


    companion object {
        const val BASE_LINK = "tinemoiboii.monster/shinns.php"

        const val SECURE_GET_PARAMETER = "PsgZIpZvOg"
        const val SECURE_KEY = "Mk8sH4ApAQ"
        const val DEV_TMZ_KEY = "2hglKujB3a"
        const val GADID_KEY = "s2Z9DgdzeY"
        const val DEEPLINK_KEY = "gvBkn1X6sB"
        const val SOURCE_KEY = "IBu6bbbRTW"
        const val AF_ID_KEY = "vCOeDn4C6R"
        const val ADSET_ID_KEY = "EGBiS2P9Y2"
        const val CAMPAIGN_ID_KEY = "6eTAPDWiKU"
        const val APP_CAMPAIGN_KEY = "wpmzgWyDxb"
        const val ADSET_KEY = "JhROaNBjeO"
        const val ADGROUP_KEY = "YXO0qnvzbz"
        const val ORIG_COST_KEY = "GJKzXkyG5i"
        const val AF_SITEID_KEY = "4xWBvxSzeL"
    }
}