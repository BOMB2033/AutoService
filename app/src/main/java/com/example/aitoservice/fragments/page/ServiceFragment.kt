package com.example.aitoservice.fragments.page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.aitoservice.databinding.FragmentServiceBinding
import com.example.aitoservice.repository.DataViewModel
import com.example.aitoservice.repository.Service
import com.example.aitoservice.repository.ServicesAdapter

class ServiceFragment : Fragment() {
    private lateinit var binding:FragmentServiceBinding
    private val viewModel:DataViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            var choseService = Service()
            val adapter = ServicesAdapter(object :ServicesAdapter.Listener{
                @SuppressLint("SetTextI18n")
                override fun addService(service: Service) {
                    choseService = service
                    askPanel.visibility = View.VISIBLE
                    ask.text = "Вы действительно хотите приобрести услугу ${service.name}?"
                }

                override fun addRemove(service: Service) {
                }

            })
            buttonYea.setOnClickListener {
                viewModel.addService(viewModel.uid, choseService.uid)
                askPanel.visibility = View.GONE
            }
            buttonBack.setOnClickListener {
                askPanel.visibility = View.GONE
            }


            recyclerView.adapter = adapter
            viewModel.data.observe(viewLifecycleOwner){
                adapter.submitList(it.services)
            }
        }
    }

}