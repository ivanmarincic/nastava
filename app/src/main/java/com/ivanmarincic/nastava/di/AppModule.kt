package com.ivanmarincic.nastava.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module(includes = [AppModule.Core::class])
class AppModule {

    @Module
    abstract class Core {
        @Binds
        abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
    }
}