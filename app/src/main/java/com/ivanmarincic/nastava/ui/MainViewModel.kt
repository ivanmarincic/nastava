package com.ivanmarincic.nastava.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanmarincic.nastava.dataaccess.api.FakultetiService
import com.ivanmarincic.nastava.dataaccess.api.StudijiService
import com.ivanmarincic.nastava.dataaccess.model.Fakultet
import com.ivanmarincic.nastava.dataaccess.model.Studij
import com.ivanmarincic.nastava.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val fakultetiService: FakultetiService,
    private val studijiService: StudijiService
) : ViewModel() {

    private val _selectedFaculty: MutableLiveData<Fakultet> = MutableLiveData()
    val selectedFaculty: LiveData<Fakultet>
        get() {
            return _selectedFaculty
        }

    private val _studijList: MutableLiveData<List<Studij>> = MutableLiveData()
    val studijList: LiveData<List<Studij>>
        get() {
            return _studijList
        }


    private val _selectedStudij: MutableLiveData<Studij> = MutableLiveData()
    val selectedStudij: LiveData<Studij>
        get() {
            return _selectedStudij
        }

    fun setFakultet(fakultet: Fakultet) {
        _selectedFaculty.value = fakultet
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                studijiService.getByFakultetId(fakultet.id)
            }.fold(
                { data -> _studijList.value = data },
                { error -> println("An error of type ${error.exception} happened: ${error.message}") }
            )
        }
    }

    fun setFakultetId(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fakultetiService.getById(id)
            }.fold(
                { data -> setFakultet(data) },
                { error -> println("An error of type ${error.exception} happened: ${error.message}") }
            )
        }
    }

    fun setStudij(studij: Studij) {
        _selectedStudij.value = studij
    }

    fun setStudijList(studiji: List<Studij>) {
        _studijList.value = studiji
    }
}