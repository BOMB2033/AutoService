package com.example.aitoservice.fragments.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aitoservice.databinding.FragmentSupportBinding

class SupportFragment : Fragment() {
    private lateinit var binding:FragmentSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSupportBinding.inflate(inflater, container, false)
        return binding.root
    }
}