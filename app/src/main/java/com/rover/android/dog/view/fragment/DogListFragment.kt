package com.rover.android.dog.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rover.android.MainActivity
import com.rover.android.MainApplication
import com.rover.android.R
import com.rover.android.databinding.DogListFragmentBinding
import com.rover.android.dog.view.adapter.DogListAdapter
import com.rover.android.dog.viewmodel.DogViewModel
import com.rover.android.dog.viewmodel.DogViewModelFactory

class DogListFragment : Fragment(), DogListAdapter.OnDogItemClickListener {

    private val dogViewModel: DogViewModel by viewModels {
        DogViewModelFactory((activity?.application as MainApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val binding = DogListFragmentBinding.inflate(inflater, container, false)

        val adapter = DogListAdapter()
        adapter.setListener(this)
        binding.dogsRecyclerView.adapter = adapter
        binding.dogsRecyclerView.layoutManager = LinearLayoutManager(context)

        dogViewModel.allDogs.observe(viewLifecycleOwner) {
            it.let {
                adapter.submitList(it)
            }
        }

        binding.addFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_dogList_to_newDog)
        }

        return binding.root
    }

    override fun onClick(name: String, uri: String) {
        findNavController().navigate(
            R.id.action_dogList_to_dogItem,
            bundleOf("dogName" to name, "dogUri" to uri)
        )
    }
}