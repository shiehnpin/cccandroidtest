package com.enping.cccandroidtest

import androidx.lifecycle.*
import com.enping.cccandroidtest.model.Estimate
import com.enping.cccandroidtest.model.Person
import com.enping.cccandroidtest.model.Record
import com.enping.cccandroidtest.repository.EstimateRecordRepository
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

class MainViewModelFactory(private val recordRepository: EstimateRecordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(recordRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MainViewModel(
    private val recordRepository: EstimateRecordRepository
) : ViewModel() {

    private val _estimateRecordLiveData = MutableLiveData<Record>()
    val estimateRecord : LiveData<Record> = _estimateRecordLiveData

    private val disposables = mutableListOf<Disposable>()

    override fun onCleared() {
        super.onCleared()
        disposables.forEach { it.dispose() }
    }

    fun insertData(estimate: Estimate) {
        viewModelScope.launch {
            recordRepository.insertEstimate(estimate)
        }
    }

    fun insertData(person: Person) {
        viewModelScope.launch {
            recordRepository.insertPerson(person)
        }
    }

    fun queryEstimateById(id: String) {
        recordRepository.getEstimateRecordById(id).subscribe {
            _estimateRecordLiveData.postValue(it)
        }.also { disposables.add(it) }
    }
}
