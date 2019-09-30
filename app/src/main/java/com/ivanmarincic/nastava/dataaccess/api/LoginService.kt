package com.ivanmarincic.nastava.dataaccess.api

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.coroutines.awaitObject
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import com.github.kittinunf.result.Result
import com.ivanmarincic.nastava.dataaccess.model.AuthData
import com.ivanmarincic.nastava.dataaccess.model.LoginData
import com.ivanmarincic.nastava.util.Constants
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginService @Inject constructor() {

    suspend fun login(): AuthData {
        return "/login"
            .httpPost()
            .jsonBody(
                Constants.mapper.writeValueAsString(
                    LoginData(
                        Constants.LOGIN_USER,
                        Constants.LOGIN_PASS
                    )
                )
            )
            .awaitObject(jacksonDeserializerOf(Constants.mapper))
    }
}