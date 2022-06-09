package com.advasoft.touchretouc.di

import android.content.Context
import com.advasoft.touchretouc.providers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class ProvidersModule {
    @Provides
    fun provideDeepLinkProvider(@ApplicationContext ctx: Context): DeepLinkProvider {
        return DeepLinkProvider(context = ctx)
    }

    @Provides
    fun provideAppsFlyerProvider(@ApplicationContext ctx: Context): AppsFlyerProvider {
        return AppsFlyerProvider(context = ctx)
    }

    @Provides
    fun provideOneSignalProvider(@ApplicationContext ctx: Context): OneSignalProvider {
        return OneSignalProvider(context = ctx)
    }

    @Provides
    fun provideAdvertisingIdClientProvider(@ApplicationContext ctx: Context): AdvertisingIdClientProvider {
        return AdvertisingIdClientProvider(context = ctx)
    }

    @Provides
    fun provideDataToUrlParser(): DataToUrlParser {
        return DataToUrlParser()
    }
}