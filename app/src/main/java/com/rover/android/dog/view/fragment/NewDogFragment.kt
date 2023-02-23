package com.rover.android.dog.view.fragment

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rover.android.MainApplication
import com.rover.android.common.view.fragment.CameraFragment
import com.rover.android.databinding.NewDogFragmentBinding
import com.rover.android.dog.model.Dog
import com.rover.android.dog.viewmodel.DogViewModel
import com.rover.android.dog.viewmodel.DogViewModelFactory

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NewDogFragment : CameraFragment() {

    private val dogViewModel: DogViewModel by viewModels {
        DogViewModelFactory((activity?.application as MainApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = NewDogFragmentBinding.inflate(inflater, container, false)

        requestCameraPermission(binding.dogPhotoPreviewView.surfaceProvider)

        binding.capturePhotoButton.setOnClickListener {
            takePhoto()
        }

        binding.saveButton.setOnClickListener {
            val dogName = binding.dogNameEditText.text

            if (!TextUtils.isEmpty(dogName)) {
                if (!TextUtils.isEmpty(uri.toString())) {
                    dogViewModel.insert(Dog(name = dogName.toString(), uri = uri.toString()))
                    Toast.makeText(context, "Dog Saved", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }

        return binding.root
    }

}