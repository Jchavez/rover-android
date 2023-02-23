package com.rover.android.dog.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.rover.android.databinding.DogListItemBinding
import com.rover.android.dog.model.Dog

class DogListAdapter : ListAdapter<Dog, DogListAdapter.DogViewHolder>(DOGS_COMPARATOR) {

    private lateinit var onDogItemClickListener: OnDogItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = DogListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name, current.uri, onDogItemClickListener)
    }

    fun setListener(onDogItemClickListener: OnDogItemClickListener) {
        this.onDogItemClickListener = onDogItemClickListener
    }

    class DogViewHolder(binding: DogListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nameDogItemView: TextView = binding.nameDogTextView
        private val photoDogItemView: ImageView = binding.photoImageView
        private val materialCardView: MaterialCardView = binding.materialCardView

        fun bind(name: String, uri: String, onDogItemClickListener: OnDogItemClickListener) {
            nameDogItemView.text = name
            photoDogItemView.setImageURI(Uri.parse(uri))
            materialCardView.setOnClickListener {
                onDogItemClickListener.onClick(name, uri)
            }
        }
    }

    companion object {
        private val DOGS_COMPARATOR = object : DiffUtil.ItemCallback<Dog>() {
            override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    interface OnDogItemClickListener {
        fun onClick(name: String, uri: String)
    }
}

