package com.ivanmarincic.nastava.ui

import android.content.Intent
import android.os.Bundle
import com.github.kittinunf.fuel.core.FuelManager
import com.ivanmarincic.nastava.dataaccess.api.LoginService
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var loginService: LoginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            val authData = loginService.login()
            FuelManager.instance.baseHeaders = mapOf("issApiAccessToken" to authData.issApiAccessToken)
            startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
            finish()
        }
    }
}