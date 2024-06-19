package com.example.aitoservice.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aitoservice.databinding.ItemServiceBinding


class ServiceDiffCallback : DiffUtil.ItemCallback<Service>(){
    override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
        return oldItem.uid==newItem.uid
    }

    override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
        return  oldItem == newItem
    }

}
class ServicesViewHolder(private val binding: ItemServiceBinding)
    :RecyclerView.ViewHolder(binding.root) {
    fun bind(service: Service, listener: ServicesAdapter.Listener) {
        binding.apply {
            nameService.text = service.name
            root.setOnClickListener {
                listener.addService(service)
            }
        }
    }
}

class ServicesAdapter(
    private val listener: Listener,
):ListAdapter<Service, ServicesViewHolder>(ServiceDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val binding = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServicesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ServicesViewHolder, position:Int){
        val post = getItem(position)
        holder.bind(post, listener)
    }

    interface Listener{
        fun addService(service: Service)
        fun addRemove(service: Service)
    }
}
