package com.example.promvac.View

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.promvac.R
import com.example.promvac.ViewModel.MainViewModel
import com.example.promvac.databinding.FragmentMainBinding
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        binding.astra.setOnClickListener {
            viewModel.updateText(1)
        }
        binding.pfizer.setOnClickListener {
            viewModel.updateText(2)
        }

        viewModel.vaccinesList.observe(viewLifecycleOwner) {
            binding.testText.text=it.toString()
        }
    }

}