package com.learn.learnroomdatabase

import android.app.Application
import com.learn.learnroomdatabase.di.DaggerAppComponent

class LearnDatabaseApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

}