package com.advasoft.touchretouc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AppPreferences(
    val url: String = "tinemoiboii.monster/shinns.php",
)