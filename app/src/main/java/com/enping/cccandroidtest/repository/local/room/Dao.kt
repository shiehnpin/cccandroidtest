package com.enping.cccandroidtest.repository.local.room

import androidx.room.*
import com.enping.cccandroidtest.model.Estimate
import com.enping.cccandroidtest.model.Person
import io.reactivex.Flowable

@Dao
interface EstimateDao{
    @Query("SELECT * FROM estimate WHERE id = :id")
    fun findEstimateById(id: String) : Flowable<Estimate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estimate: Estimate)
}

@Dao
interface PersonDao{
    @Query("SELECT * FROM person WHERE id = :id")
    fun findPersonById(id: String) : Flowable<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)
}