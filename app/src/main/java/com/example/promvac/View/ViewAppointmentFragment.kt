package com.example.promvac.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.promvac.R
import com.example.promvac.databinding.FragmentViewAppointmentBinding

class ViewAppointmentFragment : Fragment() {

    companion object {
        fun newInstance() = ViewAppointmentFragment()
    }

    private lateinit var binding:FragmentViewAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentViewAppointmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.BtnClickSearch.setOnClickListener {
            try{//prevent null input from users
                val nextFragment = showVaccineFragment()
                nextFragment.arguments=Bundle(2).apply {
                    putBoolean("Search",true)
                    putInt("PatientID",Integer.parseInt(binding.editTextTextPersonName.text.toString()))
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container,nextFragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }catch (e: NumberFormatException){
                Toast.makeText(context,"The entered value cannot be converted to integer",Toast.LENGTH_SHORT).show()
            }
        }
    }

}