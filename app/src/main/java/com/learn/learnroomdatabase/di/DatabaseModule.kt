package com.learn.learnroomdatabase.di

import android.app.Application
import androidx.room.Room
import com.learn.learnroomdatabase.ContactDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideContactDatabase(application: Application): ContactDatabase {
        return Room.databaseBuilder(
            application,
            ContactDatabase::class.java,
            "contact_db"
        ).build()
    }

}