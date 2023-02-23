package com.rover.android.dog.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rover.android.common.database.RoverRoomDatabase
import com.rover.android.common.util.getOrAwaitValue
import com.rover.android.dog.model.Dog
import com.rover.android.dog.repository.DogRepository
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DogViewModelTest: TestCase() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test Subject
    private lateinit var dogViewModel: DogViewModel
    private lateinit var roverRoomDatabase: RoverRoomDatabase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        roverRoomDatabase = Room.inMemoryDatabaseBuilder(
            context,
            RoverRoomDatabase::class.java
        ).allowMainThreadQueries().build()

        val dogRepository = DogRepository(roverRoomDatabase.dogDao())
        dogViewModel = DogViewModel(dogRepository)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        roverRoomDatabase.close()
    }

    @Test
    fun insertAndGetAllDogs() {
        // Given
        val dog = Dog(name = "Lola", uri = "Lola..jpg")

        // When
        dogViewModel.insert(dog)

        // Then
        val result = dogViewModel.allDogs.getOrAwaitValue()
        assertEquals(result[0].name, dog.name)
        assertEquals(result[0].uri, dog.uri)
    }
}