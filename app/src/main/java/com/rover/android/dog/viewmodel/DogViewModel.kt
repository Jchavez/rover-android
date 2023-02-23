package com.rover.android.dog.viewmodel

import androidx.lifecycle.*
import com.rover.android.dog.model.Dog
import com.rover.android.dog.repository.DogRepository
import kotlinx.coroutines.launch

class DogViewModel(private val dogRepository: DogRepository): ViewModel() {

    var allDogs: LiveData<List<Dog>> = dogRepository.retrieveDogs.asLiveData()

    fun insert(dog: Dog) = viewModelScope.launch {
        dogRepository.insert(dog)
    }
}

/*
* By using viewModels and ViewModelProvider.Factory,the framework will take care of the lifecycle of the ViewModel.
* It will survive configuration changes and even if the Activity is recreated, always get the right instance
* of the DogViewModel class.
* */
class DogViewModelFactory(private val dogRepository: DogRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DogViewModel(dogRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}