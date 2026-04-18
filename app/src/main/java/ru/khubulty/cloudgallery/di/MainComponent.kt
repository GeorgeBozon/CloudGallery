package ru.khubulty.cloudgallery.di

import dagger.Component
import ru.khubulty.cloudgallery.MainActivity
import javax.inject.Singleton

@Component(modules = [MainActivityModule::class])
@Singleton
interface MainComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory{

        fun newComponent(): MainComponent
    }
}