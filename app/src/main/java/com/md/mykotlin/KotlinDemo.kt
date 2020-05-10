package com.md.mykotlin

import android.app.Application
import androidx.room.Room
import com.md.database.config.AppDatabase
import com.md.mykotlin.utils.preferences.ApplicationPreferences

class KotlinDemo : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationPreferences.init(this)

        // Database
        KotlinDemo.database = Room.databaseBuilder(
            this, AppDatabase::class.java,
            "my-demo-db"
        ).build()
    }

}