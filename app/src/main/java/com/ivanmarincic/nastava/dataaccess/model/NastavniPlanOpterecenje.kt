package com.ivanmarincic.nastava.dataaccess.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NastavniPlanOpterecenje(
    val brojSati: Int = -1,
    val naziv: String = "",
    val sifra: String = ""
)