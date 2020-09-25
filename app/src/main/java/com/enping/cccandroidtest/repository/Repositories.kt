package com.enping.cccandroidtest.repository

import com.enping.cccandroidtest.model.Estimate
import com.enping.cccandroidtest.model.Person
import com.enping.cccandroidtest.model.Record
import com.enping.cccandroidtest.repository.local.room.EstimateDao
import com.enping.cccandroidtest.repository.local.room.PersonDao
import io.reactivex.Flowable
import io.reactivex.functions.Function4

class EstimateRecordRepository(
    private val estimateRepository: EstimateRepository,
    private val personRepository: PersonRepository
) {
    fun getEstimateRecordById(id: String): Flowable<Record> {
        return estimateRepository.getById(id).flatMap {
            Flowable.zip(
                Flowable.just(it),
                personRepository.getById(it.contact),
                personRepository.getById(it.createdBy),
                personRepository.getById(it.requestedBy),
                Function4<Estimate, Person, Person, Person, Record> { est, contact, createdBy, requestedBy ->
                    Record(
                        id = est.id,
                        address = est.address,
                        company = est.company,
                        revisionNumber = est.revisionNumber,
                        number = est.number,
                        contact = contact,
                        createdBy = createdBy,
                        requestedBy = requestedBy,
                        createdDatetime = est.createdDate
                    )
                }
            )
        }
    }

    suspend fun insertPerson(person: Person) {
        personRepository.insert(person)
    }

    suspend fun insertEstimate(estimate: Estimate) {
        estimateRepository.insert(estimate)
    }
}

class EstimateRepository(private val dao: EstimateDao) {

    fun getById(id: String): Flowable<Estimate> {
        return dao.findEstimateById(id)
    }

    suspend fun insert(estimate: Estimate) {
        dao.insert(estimate)
    }
}

class PersonRepository(private val dao: PersonDao) {

    fun getById(id: String): Flowable<Person> {
        return dao.findPersonById(id)
    }

    suspend fun insert(person: Person) {
        dao.insert(person)
    }
}