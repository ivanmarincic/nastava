package com.ivanmarincic.nastava.dataaccess.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

interface PlanWithSemestar {
    val semestar: Int
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class NastavniPlan(
    val akademskaGodina: String = "",
    val ectsBodovi: Int = -1,
    val faksId: Int = -1,
    val id: Long = -1L,
    val nositeljId: Nastavnik = Nastavnik(),
    val opterecenjeList: List<NastavniPlanOpterecenje> = emptyList(),
    val ptiId: Kolegij = Kolegij(),
    val rbr: Int = -1,
    val sdiId: Studij = Studij(),
    val tip: String = "",
    override val semestar: Int = -1
) : PlanWithSemestar {

    val opterecenjePredavanja: Int by lazy {
        opterecenjeList.firstOrNull { it.sifra == "P" }?.brojSati ?: 0
    }

    val opterecenjeVjezbe: Int by lazy {
        opterecenjeList.firstOrNull { it.sifra == "VJ" }?.brojSati ?: 0
    }

    val opterecenjeSeminari: Int by lazy {
        opterecenjeList.firstOrNull { it.sifra == "S" }?.brojSati ?: 0
    }
}

data class NastavniPlanHeader(override val semestar: Int = -1) : PlanWithSemestar