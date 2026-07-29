package ru.khubulty.navigationImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import ru.khubulty.navigationApi.Navigator

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface NavigationModule {

    @Binds
    @ActivityRetainedScoped
    fun provideNavigator(defaultNavigator: DefaultNavigator): Navigator

}