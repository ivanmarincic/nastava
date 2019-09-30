package com.ivanmarincic.nastava.dataaccess.api

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import com.github.kittinunf.result.Result
import com.ivanmarincic.nastava.dataaccess.model.NastavniPlan
import com.ivanmarincic.nastava.util.Constants
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NastavniPlanoviService @Inject constructor() {

    suspend fun getByStudij(
        fakultetId: Int,
        studijId: Long,
        akademskaGodina: String
    ): Result<List<NastavniPlan>, FuelError> {
        return "/fakulteti/$fakultetId/studiji/$studijId/nastavniPlanovi?akademskaGodina=$akademskaGodina&order=semestar"
            .httpGet()
            .awaitObjectResult(jacksonDeserializerOf(Constants.mapper))
    }
}