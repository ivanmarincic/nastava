package com.ivanmarincic.nastava.dataaccess.api

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import com.github.kittinunf.result.Result
import com.ivanmarincic.nastava.dataaccess.model.Fakultet
import com.ivanmarincic.nastava.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakultetiService @Inject constructor() {

    suspend fun getAll(): Result<List<Fakultet>, FuelError> {
        return "/fakulteti"
            .httpGet()
            .awaitObjectResult(jacksonDeserializerOf(Constants.mapper))
    }

    suspend fun getById(id: Int): Result<Fakultet, FuelError> {
        return "/fakulteti/$id"
            .httpGet()
            .awaitObjectResult(jacksonDeserializerOf(Constants.mapper))
    }
}