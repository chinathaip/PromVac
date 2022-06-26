package com.example.promvac.View

import android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
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
            try{//prevent null input from users
                viewModel.updateRecyclerView(Integer.parseInt(binding.editTextTextPersonName.text.toString()))
            }catch (e: NumberFormatException){
                Toast.makeText(context,"The entered value cannot be converted to integer",Toast.LENGTH_SHORT).show()
            }
        }

        var adapter=VaccinesHistoryRVadapter(viewModel.list)
        viewModel.vaccineOfPatient.observe(viewLifecycleOwner){
            Log.i("LOL",it.toString())
            adapter.vaccineList=it
            adapter.notifyDataSetChanged()
        }

        binding.RVvaccineHistory.adapter=adapter
        binding.RVvaccineHistory.layoutManager=LinearLayoutManager(context)



    }

}