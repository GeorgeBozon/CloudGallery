package ru.khubulty.authImpl.features.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import ru.khubulty.navigationApi.Navigator

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object LoginModule {

    @IntoSet
    @Provides
    fun provideLoginDestinationBuilder(navigator: Navigator): EntryProviderScope<NavKey>.(Navigator) -> Unit =
        { loginDestinationsBuilder(navigator) }

}