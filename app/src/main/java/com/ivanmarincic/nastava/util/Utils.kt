package com.ivanmarincic.nastava.util

import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoField


class Utils {
    companion object {
        fun getAcademicYear(): String {
            val now = LocalDateTime.now()
            return if (now.get(ChronoField.MONTH_OF_YEAR) >= 10) {
                "${now.get(ChronoField.YEAR)}/${now.get(ChronoField.YEAR) + 1}"
            } else {
                "${now.get(ChronoField.YEAR) - 1}/${now.get(ChronoField.YEAR)}"
            }
        }
    }
}