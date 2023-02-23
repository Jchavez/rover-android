package com.rover.android.dog.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rover.android.common.database.RoverRoomDatabase
import com.rover.android.dog.model.Dog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DogDaoTest {

    private lateinit var dogDao: DogDao
    private lateinit var db: RoverRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, RoverRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dogDao = db.dogDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetDog() = runBlocking {
        // Given
        val dog = Dog(name = "Fluffy", uri = "src/fluffy.jpg")
        dogDao.insert(dog)

        // When
        val dogs = dogDao.retrieveDogs().first()

        // Then
        assertEquals(dogs[0].name, dog.name)
        assertEquals(dogs[0].uri, dog.uri)
    }

    @Test
    @Throws(Exception::class)
    fun retrieveDogs() = runBlocking {
        // Given
        val dog1 = Dog(name = "Fluffy", uri = "src/fluffy.jpg")
        dogDao.insert(dog1)

        val dog2 = Dog(name = "Lola", uri = "src/lola.jpg")
        dogDao.insert(dog2)

        // When
        val dogs = dogDao.retrieveDogs().first()

        // Then
        assertEquals(dogs[0].name, dog1.name)
        assertEquals(dogs[0].uri, dog1.uri)

        assertEquals(dogs[1].name, dog2.name)
        assertEquals(dogs[1].uri, dog2.uri)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        // Given
        val dog1 = Dog(name = "Fluffy", uri = "src/fluffy.jpg")
        dogDao.insert(dog1)

        val dog2 = Dog(name = "Lola", uri = "src/lola.jpg")
        dogDao.insert(dog2)

        // When
        dogDao.deleteAll()

        // Then
        val dogs = dogDao.retrieveDogs().first()
        assertTrue(dogs.isEmpty())
    }
}