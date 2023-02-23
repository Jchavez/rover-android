package com.rover.android.dog.repository

import com.rover.android.dog.dao.DogDao
import com.rover.android.dog.model.Dog
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class DogRepositoryTest {

    // Test Subject
    private lateinit var dogRepository: DogRepository

    // Dependencies
    @RelaxedMockK private lateinit var dogDao: DogDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dogRepository = DogRepository(dogDao)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun retrieveDogs() {
        // Given
        val flowDogs = flow {
            emit(listOf(Dog(name = "Lola", uri = "lola.jpg")))
        }
        every { dogDao.retrieveDogs() } returns flowDogs

        // When
        dogRepository.retrieveDogs

        // Then
        verify {
            dogDao.retrieveDogs()
        }
    }

    @Test
    fun insertDog() {
        runBlocking {
            // Given
            val dog = Dog(name = "Lola", uri = "lola.jpg")

            // When
            dogRepository.insert(dog = dog)

            // Then
            coVerify {
                dogDao.insert(dog)
            }
        }
    }
}