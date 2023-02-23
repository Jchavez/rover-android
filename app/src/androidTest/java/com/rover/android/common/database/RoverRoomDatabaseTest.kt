package com.rover.android.common.database

import android.content.Context

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rover.android.dog.dao.DogDao
import com.rover.android.dog.model.Dog
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoverRoomDatabaseTest : TestCase() {

    private lateinit var dogDao: DogDao
    private lateinit var db: RoverRoomDatabase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context,
            RoverRoomDatabase::class.java
        ).build()

        dogDao = db.dogDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndRetrieveDog() {
        runBlocking {
            // Given
            val dog = Dog(name = "Lola", uri = "lola.jpg")

            // When
            dogDao.insert(dog)
            val dogs = dogDao.retrieveDogs()

            // Then
            assertEquals(dogs.first()[0].name, dog.name)
            assertEquals(dogs.first()[0].uri, dog.uri)
        }
    }
}