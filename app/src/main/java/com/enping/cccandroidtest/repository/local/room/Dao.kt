package com.enping.cccandroidtest.repository.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.enping.cccandroidtest.model.Estimate
import com.enping.cccandroidtest.model.Person

@Dao
interface EstimateDao{
    @Query("SELECT * FROM estimate WHERE id = :id")
    suspend fun findEstimateById(id: String) : Estimate

    @Insert
    suspend fun insert(estimate: Estimate)
}

@Dao
interface PersonDao{
    @Query("SELECT * FROM person WHERE id = :id")
    suspend fun findPersonById(id: String) : Person

    @Insert
    suspend fun insert(person: Person)
}