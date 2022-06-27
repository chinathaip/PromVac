package com.example.promvac.View

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.promvac.R
import com.example.promvac.ViewModel.MainViewModel
import com.example.promvac.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.example.promvac.Model.Vaccines as Vaccines

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var  binding:FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //perform view binding
        binding=FragmentMainBinding.inflate(layoutInflater)
        return binding.root //return the view of the fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        binding.BtnBookVac.setOnClickListener {
            Toast.makeText(this.context,"Going to the book a vaccine page",Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container,BookVaccineFragment.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit()

        }

        binding.BtnViewApp.setOnClickListener {
            Toast.makeText(this.context,"Going to the view appointment page",Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container,ViewAppointmentFragment.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit()

        }

//
//        binding.astra.setOnClickListener {
//            //CoroutineScope can contain: Main(main thread), IO(input/output thread), Default(for heavy computation)
//            CoroutineScope(Main).launch {
//                viewModel.updateText(1)
//            }
//
//        }
//        binding.pfizer.setOnClickListener {
//            CoroutineScope(Main).launch {
//                viewModel.updateText(2)
//            }
//
//        }
//
//        viewModel.vaccinesList.observe(viewLifecycleOwner) {
//            binding.testText.text=it.toString()
//        }
    }

}