package com.ivanmarincic.nastava.dataaccess.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Studij(
    val akademskiStupanj: String = "",
    val faks: String = "",
    val faksId: Int = -1,
    val id: Long = -1L,
    val izvedba: String = "",
    val kratica: String = "",
    val nPredmetni: String = "",
    val naziv: String = "",
    val sifra: String = "",
    val trajanje: Int = -1,
    val webPrikaz: String = ""
)