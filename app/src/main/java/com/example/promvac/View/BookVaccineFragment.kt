package com.example.promvac.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.promvac.Model.Vaccines
import com.example.promvac.ViewModel.BookVaccineViewModel
import com.example.promvac.databinding.FragmentBookVaccineBinding
import java.util.*

class BookVaccineFragment : Fragment() {

    companion object {
        fun newInstance() = BookVaccineFragment()
    }

    private lateinit var viewModel: BookVaccineViewModel
    private lateinit var binding: FragmentBookVaccineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentBookVaccineBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookVaccineViewModel::class.java)
        // TODO: Use the ViewModel

        binding.btnSubmit.setOnClickListener {
            val patientName = binding.inputName.text.toString()
            val chosenVaccine = binding.root.findViewById<RadioButton>(binding.vaccineGroup.checkedRadioButtonId).text.toString()
            val calendar= binding.inputDate
            val firstDoseDate= Date(calendar.year-1900,calendar.month,calendar.dayOfMonth)
            val chosenHospital = binding.inputHospital.selectedItem.toString()
            chosenVaccine.let{vaccineName->
                viewModel.createVaccine(vaccineName,firstDoseDate,chosenHospital)
            }.run{
                //this = first dose vaccine created from .let{}
                //we take booster dose every 3 months, so we do month + 3 for the next appointment
                val secondDoseDate = Date(calendar.year-1900,calendar.month+3,calendar.dayOfMonth)
                val secondDose = Vaccines(this.vacName,secondDoseDate,this.hospital)
                viewModel.createNewAppointment(patientName,this,secondDose)
            }

//            viewModel.updateCurrentPatientID()

        }
    }

}