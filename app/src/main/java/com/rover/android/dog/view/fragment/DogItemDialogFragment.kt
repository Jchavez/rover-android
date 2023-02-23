package com.rover.android.dog.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rover.android.databinding.DogItemDialogFragmentBinding

class DogItemDialogFragment : BottomSheetDialogFragment() {

    private val dogName: String?
        get() = arguments?.getString("dogName")

    private val dogUri: String?
        get() = arguments?.getString("dogUri")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DogItemDialogFragmentBinding.inflate(inflater, container, false)

        binding.nameDogItemTextView.text = dogName
        binding.photoDogItemImageView.setImageURI(Uri.parse(dogUri))

        return binding.root
    }
}