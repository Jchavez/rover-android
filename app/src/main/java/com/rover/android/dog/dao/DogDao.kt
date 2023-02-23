package com.rover.android.dog.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rover.android.dog.model.Dog
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    @Query("SELECT * FROM dogs ORDER BY name ASC")
    fun retrieveDogs(): Flow<List<Dog>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dog: Dog)

    @Query("DELETE FROM dogs")
    suspend fun deleteAll()
}