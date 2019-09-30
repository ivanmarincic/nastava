package com.ivanmarincic.nastava.util

import com.fasterxml.jackson.databind.ObjectMapper

class Constants {

    companion object {
        const val API_BASE_URL = "https://is.sum.ba:4443/ISSApi/resources"
        const val LOGIN_USER = "FPMOZ_ISSAPI"
        const val LOGIN_PASS = "issapi"

        lateinit var mapper: ObjectMapper
    }
}