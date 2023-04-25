package com.learn.learnroomdatabase.di

import android.app.Application
import com.learn.learnroomdatabase.ContactDatabase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }


    fun getContactDatabase(): ContactDatabase

}