package com.example.aitoservice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.aitoservice.R
import com.example.aitoservice.databinding.FragmentMainBinding
import com.example.aitoservice.repository.DataViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding
    private lateinit var navController : NavController
    private val viewModel:DataViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
        binding.bottomNavigationView.setupWithNavController(navController)

        viewLifecycleOwner.lifecycleScope.launch {
            while (viewModel.data.value!!.user.uid == "")
                delay(1000)
            binding.progressBar.visibility = View.GONE
        }

    }

}