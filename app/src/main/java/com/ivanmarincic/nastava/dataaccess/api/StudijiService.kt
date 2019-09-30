package com.ivanmarincic.nastava.dataaccess.api

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import com.github.kittinunf.result.Result
import com.ivanmarincic.nastava.dataaccess.model.Studij
import com.ivanmarincic.nastava.util.Constants
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StudijiService @Inject constructor() {

    suspend fun getByFakultetId(id: Int): Result<List<Studij>, FuelError> {
        return "/fakulteti/$id/studiji"
            .httpGet()
            .awaitObjectResult(jacksonDeserializerOf(Constants.mapper))
    }

    suspend fun getById(id: Long): Result<Studij, FuelError> {
        return "/fakulteti//studiji/$id"
            .httpGet()
            .awaitObjectResult(jacksonDeserializerOf(Constants.mapper))
    }
}