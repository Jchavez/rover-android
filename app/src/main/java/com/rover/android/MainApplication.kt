package com.rover.android

import android.app.Application
import com.rover.android.common.database.RoverRoomDatabase
import com.rover.android.dog.repository.DogRepository

class MainApplication: Application() {

    private val database by lazy { RoverRoomDatabase.getDatabase(this) }
    val repository by lazy { DogRepository(database.dogDao()) }
}