package com.advasoft.touchretouc.di

import android.content.Context
import com.advasoft.touchretouc.data.repo.ShiningEgyptSecurityRepository
import com.advasoft.touchretouc.providers.SecurityProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    @Provides
    fun provideSecurityProvider(
        @ApplicationContext ctx: Context,
        repository: ShiningEgyptSecurityRepository
    ): SecurityProvider {
        return SecurityProvider(context = ctx, repository = repository)
    }
}