package com.rover.android.common.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rover.android.dog.dao.DogDao
import com.rover.android.dog.model.Dog

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class RoverRoomDatabase: RoomDatabase() {

    abstract fun dogDao(): DogDao

    // Singleton prevents multiple instances of database opening at the same time.
    companion object {
        @Volatile private var INSTANCE: RoverRoomDatabase? = null

        fun getDatabase(context: Context): RoverRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoverRoomDatabase::class.java,
                    "dog_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}