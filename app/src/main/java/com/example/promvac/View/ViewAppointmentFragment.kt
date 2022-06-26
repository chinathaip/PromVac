package com.example.promvac.View

import android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.promvac.R
import com.example.promvac.ViewModel.ViewAppointmentViewModel
import com.example.promvac.databinding.FragmentViewAppointmentBinding

class ViewAppointmentFragment : Fragment() {

    companion object {
        fun newInstance() = ViewAppointmentFragment()
    }

    private lateinit var binding:FragmentViewAppointmentBinding
    private lateinit var viewModel: ViewAppointmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentViewAppointmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewAppointmentViewModel::class.java)
        // TODO: Use the ViewModel


        binding.BtnClickSearch.setOnClickListener {
            val patientAppointmentFragment = PatientAppointmentFragment() //create new instance of the new fragment
            try{//prevent null input from users
                patientAppointmentFragment.arguments=Bundle(1).apply{
                    //pass value from userinput to another fragment (patientAppointmentFragment)
                    putInt("PatientID",Integer.parseInt(binding.editTextTextPersonName.text.toString()))
                }
                childFragmentManager.beginTransaction()
                    .replace(binding.patientInfoFragment.id,patientAppointmentFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commitNow()
            }catch (e: NumberFormatException){
                Toast.makeText(context,"The entered value cannot be converted to integer",Toast.LENGTH_SHORT).show()
            }

        }



    }

}