package com.enping.cccandroidtest.repository

import com.enping.cccandroidtest.model.Estimate
import com.enping.cccandroidtest.model.Person
import com.enping.cccandroidtest.model.Record
import com.enping.cccandroidtest.repository.local.room.EstimateDao
import com.enping.cccandroidtest.repository.local.room.PersonDao

class EstimateRecordRepository(
    private val estimateRepository: EstimateRepository,
    private val personRepository: PersonRepository
) {
    suspend fun getEstimateRecordById(id: String): Record{
        val est = estimateRepository.getById(id)

        return Record(
            id = est.id,
            address = est.address,
            company = est.company,
            revisionNumber = est.revisionNumber,
            number = est.number,
            contact = personRepository.getById(est.contact),
            createdBy = personRepository.getById(est.createdBy),
            requestedBy = personRepository.getById(est.requestedBy),
            createdDate = est.createdDate
        )
    }

    suspend fun insertPerson(person: Person) {
        personRepository.insert(person)
    }

    suspend fun insertEstimate(estimate: Estimate) {
        estimateRepository.insert(estimate)
    }
}

class EstimateRepository(private val dao: EstimateDao) {

    suspend fun getById(id: String): Estimate {
        return dao.findEstimateById(id)
    }

    suspend fun insert(estimate: Estimate) {
        dao.insert(estimate)
    }
}

class PersonRepository(private val dao: PersonDao) {

    suspend fun getById(id: String): Person {
        return dao.findPersonById(id)
    }

    suspend fun insert(person: Person) {
        dao.insert(person)
    }
}