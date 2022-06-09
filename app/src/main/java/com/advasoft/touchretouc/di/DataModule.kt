package com.advasoft.touchretouc.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import com.advasoft.touchretouc.data.model.AppPreferences
import com.advasoft.touchretouc.data.repo.ShiningEgyptProtoRepository
import com.advasoft.touchretouc.data.repo.ShiningEgyptSecurityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Singleton

private const val APP_PREFERENCES_NAME = "app_preferences"
private const val DATASTORE_FILENAME = "app_preferences.json"

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideShiningEgyptProtoRepository(
        prefDataStore: DataStore<AppPreferences>
    ): ShiningEgyptProtoRepository {
        return ShiningEgyptProtoRepository(dataStore = prefDataStore)
    }

    @Provides
    @Singleton
    fun provideShiningEgyptSecurityRepository(
    ): ShiningEgyptSecurityRepository {
        return ShiningEgyptSecurityRepository()
    }

    @Provides
    @Singleton
    @Suppress("BlockingMethodInNonBlockingContext")
    fun provideAppPreferencesSerializer(): Serializer<AppPreferences> {
        return object : Serializer<AppPreferences> {
            override val defaultValue: AppPreferences
                get() = AppPreferences()

            override suspend fun readFrom(input: InputStream): AppPreferences =
                try {
                    Json.decodeFromString(
                        deserializer = AppPreferences.serializer(),
                        string = input.readBytes().decodeToString()
                    )
                } catch (e: SerializationException) {
                    e.printStackTrace()
                    defaultValue
                }

            override suspend fun writeTo(t: AppPreferences, output: OutputStream) {
                output.write(
                    Json.encodeToString(
                        serializer = AppPreferences.serializer(),
                        value = t
                    ).encodeToByteArray()
                )
            }
        }
    }

    @Provides
    @Singleton
    fun provideProtoDataStore(
        @ApplicationContext ctx: Context,
        appPrefSerializer: Serializer<AppPreferences>
    ): DataStore<AppPreferences> {
        return DataStoreFactory.create(
            serializer = appPrefSerializer,
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { ctx.dataStoreFile(DATASTORE_FILENAME) }
        )
    }
}