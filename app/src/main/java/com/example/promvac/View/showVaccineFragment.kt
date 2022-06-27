package com.example.promvac.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promvac.R
import com.example.promvac.ViewModel.ShowVaccineViewModel
import com.example.promvac.databinding.FragmentShowVaccineBinding

class showVaccineFragment : Fragment() {

    companion object {
        fun newInstance() = showVaccineFragment()
    }

    private lateinit var viewModel: ShowVaccineViewModel
    private lateinit var binding :FragmentShowVaccineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(ShowVaccineViewModel::class.java)
        // TODO: Use the ViewModel

        arguments?.getBoolean("Search").let {
            if(it==true){
                Log.i("LOL","TRUE")
                val patientID = arguments?.getInt("PatientID")
                viewModel.updateRecyclerView(patientID)
            }
        }
        binding = FragmentShowVaccineBinding.inflate(layoutInflater)

        return binding.root //MUST RETURN binding.root if you want to use binding properly
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("LOL","List ${viewModel.list}")

        val adapter=VaccinesHistoryRVadapter(viewModel.list)
        val linearLayoutManager=LinearLayoutManager(this.context)

        viewModel.vaccineOfPatient.observe(viewLifecycleOwner){
            Log.i("LOL",it.toString())
            adapter.vaccineList=it
            adapter.notifyDataSetChanged()
        }


        viewModel.patientName.observe(viewLifecycleOwner){
            binding.patientName.text=getString(R.string.greetPatient,it)
        }
        binding.vaccineHistoryRV.adapter=adapter
        binding.vaccineHistoryRV.layoutManager=linearLayoutManager
    }

}