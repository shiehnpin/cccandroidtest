package com.enping.cccandroidtest

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.enping.cccandroidtest.model.Estimate
import com.enping.cccandroidtest.model.Person
import com.enping.cccandroidtest.repository.EstimateRecordRepository
import com.enping.cccandroidtest.repository.EstimateRepository
import com.enping.cccandroidtest.repository.PersonRepository
import com.enping.cccandroidtest.repository.local.room.RecordDatabase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(DebugTree())

        GlobalScope.launch {
            val repo = createRepo(this@MainActivity)
            insertSampleData(repo, this@MainActivity)
            val result = repo.getEstimateRecordById("c574b0b4-bdef-4b92-a8f0-608a86ccf5ab");
            Timber.d(result.toString())
        }
    }

    private suspend fun insertSampleData(
        recordRepository: EstimateRecordRepository,
        context: Context
    ) {
        withContext(Dispatchers.IO) {
            val estSample = context.assets.open("est_sample.json").use {
                Gson().fromJson(InputStreamReader(it).readText(), Estimate::class.java)
            }
            val personSample = context.assets.open("person_sample.json").use {
                Gson().fromJson(InputStreamReader(it).readText(), Person::class.java)
            }
            recordRepository.insertEstimate(estSample)
            recordRepository.insertPerson(personSample)
        }
    }
}

private fun createRepo(
    context: Context
): EstimateRecordRepository {
    val db = RecordDatabase.getDatabase(context)
    val estRepo = EstimateRepository(db.estimateDao())
    val personRepo = PersonRepository(db.personDao())
    return EstimateRecordRepository(estRepo, personRepo)
}
