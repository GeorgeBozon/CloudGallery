package ru.khubulty.cloudgallery

import android.app.Application
import ru.khubulty.cloudgallery.di.DaggerMainComponent
import ru.khubulty.cloudgallery.di.MainComponent

class App: Application() {
    lateinit var daggerComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerMainComponent.create()
    }
}

fun Application.component() = (this as App).daggerComponent