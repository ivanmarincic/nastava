package com.ivanmarincic.nastava.dataaccess.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Nastavnik(
    val akademskiStupanj: String = "",
    val eMail: String = "",
    val fakultet: String = "",
    val id: Long = -1L,
    val ime: String = "",
    val prezime: String = "",
    val telefon: String = "",
    val zvanje: String = ""
) {

    val punoIme: String by lazy {
        "$akademskiStupanj $zvanje $ime $prezime".trim().replace("\\s+".toRegex(), " ")
    }
}