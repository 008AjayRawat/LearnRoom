package com.learn.learnroomdatabase.di

import com.learn.learnroomdatabase.MainActivity
import dagger.Component

@MainActivityScope
@Component(dependencies = [AppComponent::class])
interface MainActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MainActivityComponent
    }


    fun inject(mainActivity: MainActivity)

}