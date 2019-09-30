package com.ivanmarincic.nastava.di

import com.ivanmarincic.nastava.di.scopes.ActivityScoped
import com.ivanmarincic.nastava.ui.LauncherActivity
import com.ivanmarincic.nastava.ui.MainActivity
import com.ivanmarincic.nastava.ui.MainModule
import com.ivanmarincic.nastava.ui.classes.ClassesModule
import com.ivanmarincic.nastava.ui.details.DetailsFragment
import com.ivanmarincic.nastava.ui.details.DetailsModule
import com.ivanmarincic.nastava.ui.settings.SettingsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            ClassesModule::class,
            SettingsModule::class,
            DetailsModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
        ]
    )
    internal abstract fun launcherActivity(): LauncherActivity
}