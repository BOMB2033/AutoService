package com.example.aitoservice.fragments.misc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.aitoservice.databinding.FragmentReportBinding

class ReportFragment : Fragment() {
    private lateinit var binding: FragmentReportBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val listItems = ArrayList<String>()
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listItems)
            listReport.adapter = adapter
            buttonSend.setOnClickListener {
                val message = editTextReport.text.toString()
                if (message.isNotEmpty()) {
                    listItems.add(message)
                    adapter.notifyDataSetChanged()
                    editTextReport.text.clear()
                }
            }
            buttonBack.setOnClickListener {
                it.findNavController().popBackStack()
            }
        }
    }
}