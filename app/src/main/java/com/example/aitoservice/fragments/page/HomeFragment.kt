package com.example.aitoservice.fragments.page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.aitoservice.R
import com.example.aitoservice.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            buttonCall.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:" + "+79515636615")
                }
                startActivity(dialIntent)
            }
            buttonRecover.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.main_fragment_container_view).navigate(R.id.action_mainFragment_to_historyFragment)
            }

            buttonReport.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.main_fragment_container_view).navigate(R.id.action_mainFragment_to_reportFragment)
            }
        }
    }
}