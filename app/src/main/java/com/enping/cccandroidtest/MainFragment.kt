package com.enping.cccandroidtest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.enping.cccandroidtest.databinding.MainFragmentBinding
import com.enping.cccandroidtest.model.Estimate
import com.enping.cccandroidtest.model.Person
import com.enping.cccandroidtest.repository.EstimateRecordRepository
import com.enping.cccandroidtest.repository.EstimateRepository
import com.enping.cccandroidtest.repository.PersonRepository
import com.enping.cccandroidtest.repository.local.room.RecordDatabase
import com.google.gson.Gson
import java.io.InputStreamReader


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var binding: MainFragmentBinding? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = MainViewModelFactory(createRepo(requireContext()))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        val _binding : MainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        _binding.mainViewModel = viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
        binding = _binding
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        insertSampleData()
        queryData()
    }

    private fun queryData() {
        viewModel.queryEstimateById("c574b0b4-bdef-4b92-a8f0-608a86ccf5ab")
    }

    private fun insertSampleData() {
        val estSample = requireContext().assets.open("est_sample.json").use {
            Gson().fromJson(InputStreamReader(it).readText(), Estimate::class.java)
        }

        val personSample = requireContext().assets.open("person_sample.json").use {
            Gson().fromJson(InputStreamReader(it).readText(), Person::class.java)
        }

        viewModel.insertData(estSample)
        viewModel.insertData(personSample)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createRepo(
        context: Context
    ) : EstimateRecordRepository {
        val db = RecordDatabase.getDatabase(context)
        val estRepo = EstimateRepository(db.estimateDao())
        val personRepo = PersonRepository(db.personDao())
        return EstimateRecordRepository(estRepo, personRepo)
    }

}
