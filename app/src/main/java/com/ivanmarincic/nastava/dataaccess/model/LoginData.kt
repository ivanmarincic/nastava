package com.ivanmarincic.nastava.dataaccess.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginData(val username: String = "", val password: String = "")