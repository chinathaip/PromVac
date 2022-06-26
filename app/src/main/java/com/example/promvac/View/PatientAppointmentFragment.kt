package com.example.promvac.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promvac.ViewModel.PatientAppointmentViewModel
import com.example.promvac.R
import com.example.promvac.databinding.FragmentPatientAppointmentBinding
import com.example.promvac.databinding.ViewholderVaccinehistoryRvBinding

class PatientAppointmentFragment : Fragment() {

    companion object {
        fun newInstance() = PatientAppointmentFragment()
    }

    private lateinit var viewModel: PatientAppointmentViewModel
    private lateinit var binding: FragmentPatientAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_patient_appointment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientAppointmentViewModel::class.java)
        binding= FragmentPatientAppointmentBinding.inflate(layoutInflater)
        // TODO: Use the ViewModel

//        viewModel.
        val patientID = arguments?.getInt("PatientID")
        viewModel.getFromDB("patients",patientID){
            Log.i("LOL",it.firstDoseDate?.hospital.toString())
            Log.i("LOL",it.secondDoseDate?.hospital.toString())

        }




//        binding.RVvaccineHistory.adapter=VaccinesHistoryRVadapter()
        binding.RVvaccineHistory.layoutManager=LinearLayoutManager(context)

    }

}