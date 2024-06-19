package com.example.aitoservice.fragments.misc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.aitoservice.databinding.FragmentHistoryBinding
import com.example.aitoservice.repository.DataViewModel
import com.example.aitoservice.repository.Service
import com.example.aitoservice.repository.ServicesAdapter

class HistoryFragment : Fragment() {
    private lateinit var binding:FragmentHistoryBinding
    private val viewModel:DataViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val adapter = ServicesAdapter(object : ServicesAdapter.Listener{
                override fun addService(service: Service) {

                }

                override fun addRemove(service: Service) {
                }

            })
            recyclerView.adapter= adapter
            viewModel.data.observe(viewLifecycleOwner){dataClass->
                adapter.submitList(dataClass.services.filter { dataClass.user.service.contains(it.uid) })
            }
            buttonBack.setOnClickListener {
                it.findNavController().popBackStack()
            }
        }
    }
}