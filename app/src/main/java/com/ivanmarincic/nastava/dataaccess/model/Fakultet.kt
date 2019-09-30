package com.ivanmarincic.nastava.dataaccess.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Fakultet(val id: Int = -1, val naziv: String = "")