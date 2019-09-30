package com.ivanmarincic.nastava.dataaccess.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Kolegij(
    val aktivnost: String = "",
    val ectsBodova: Int = -1,
    val faksId: Int = -1,
    val id: Long = -1L,
    val kratica: String = "",
    val naziv: String = "",
    val sifra: String = "",
    val tip: String = ""
)