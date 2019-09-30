package com.ivanmarincic.nastava.ui.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanmarincic.nastava.dataaccess.api.FakultetiService
import com.ivanmarincic.nastava.dataaccess.api.NastavniPlanoviService
import com.ivanmarincic.nastava.dataaccess.model.Fakultet
import com.ivanmarincic.nastava.dataaccess.model.NastavniPlan
import com.ivanmarincic.nastava.dataaccess.model.Studij
import com.ivanmarincic.nastava.databinding.ItemClassBinding
import com.ivanmarincic.nastava.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ClassesViewModel @Inject constructor(
    private val fakultetiService: FakultetiService,
    private val nastavniPlanoviService: NastavniPlanoviService
) : ViewModel(), FacultyEventListener, ClassEventListener {

    private val _facultyList: MutableLiveData<List<Fakultet>> = MutableLiveData()
    val facultyList: LiveData<List<Fakultet>>
        get() {
            return _facultyList
        }

    private val _nastavniPlanList: MutableLiveData<List<NastavniPlan>> = MutableLiveData()
    val nastavniPlanList: LiveData<List<NastavniPlan>>
        get() {
            return _nastavniPlanList
        }

    private val _selectedFaculty: MutableLiveData<Fakultet> = MutableLiveData()
    val selectedFaculty: LiveData<Fakultet>
        get() {
            return _selectedFaculty
        }

    private val _selectedNastavniPlan: MutableLiveData<NastavniPlan> = MutableLiveData()
    val selectedNastavniPlan: LiveData<NastavniPlan>
        get() {
            return _selectedNastavniPlan
        }

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fakultetiService.getAll()
            }.fold(
                { data -> _facultyList.value = data },
                { error -> println("An error of type ${error.exception} happened: ${error.message}") }
            )
        }
    }

    fun loadNastavniPlan(fakultet: Fakultet, studij: Studij) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                nastavniPlanoviService.getByStudij(fakultet.id, studij.id, Utils.getAcademicYear())
            }.fold(
                { data -> _nastavniPlanList.value = data },
                { error -> println("An error of type ${error.exception} happened: ${error.message}") }
            )
        }
    }

    override fun selectFakultet(item: Fakultet) {
        _selectedFaculty.value = item
    }

    override fun selectNastavniPlan(item: NastavniPlan) {
        _selectedNastavniPlan.value = item
    }
}

interface FacultyEventListener {
    fun selectFakultet(item: Fakultet)
}

interface ClassEventListener {
    fun selectNastavniPlan(item: NastavniPlan)
}