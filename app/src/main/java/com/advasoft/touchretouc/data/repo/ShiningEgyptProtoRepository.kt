package com.advasoft.touchretouc.data.repo

import androidx.datastore.core.DataStore
import com.advasoft.touchretouc.data.model.AppPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ShiningEgyptProtoRepository(private val dataStore: DataStore<AppPreferences>) {
    suspend fun updateUrl(newUrl: String) {
        if (getCurrentUrl().contains("tinemoiboii.monster/shinns.php"))
            dataStore.updateData { it.copy(url = newUrl) }
    }

    suspend fun getCurrentUrl(): String {
        return dataStore.data.map { it.url }.first()
    }
}