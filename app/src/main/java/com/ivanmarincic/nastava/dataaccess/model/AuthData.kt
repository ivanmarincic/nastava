package com.ivanmarincic.nastava.dataaccess.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AuthData(val issApiAccessToken: String = "", val role: String = "", val username: String = "")