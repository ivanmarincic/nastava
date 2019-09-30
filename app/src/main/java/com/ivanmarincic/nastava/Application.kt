package com.ivanmarincic.nastava

import androidx.appcompat.app.AppCompatDelegate
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.core.FuelManager
import com.ivanmarincic.nastava.di.DaggerAppComponent
import com.ivanmarincic.nastava.util.Constants
import com.ivanmarincic.nastava.util.SharedPreferenceStorage
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class Application : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(SharedPreferenceStorage(this).darkTheme)
        AndroidThreeTen.init(this)
        FuelManager.instance.basePath = Constants.API_BASE_URL
        Constants.mapper = ObjectMapper()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .factory()
            .create(this)
    }

}