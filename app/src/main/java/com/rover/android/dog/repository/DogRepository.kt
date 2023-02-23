package com.rover.android.dog.repository

import androidx.annotation.WorkerThread
import com.rover.android.dog.dao.DogDao
import com.rover.android.dog.model.Dog
import kotlinx.coroutines.flow.Flow

class DogRepository(private val dogDao: DogDao) {

    val retrieveDogs: Flow<List<Dog>> = dogDao.retrieveDogs()
    
    @WorkerThread
    suspend fun insert(dog: Dog) {
        dogDao.insert(dog)
    }
}