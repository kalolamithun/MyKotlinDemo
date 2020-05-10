package com.md.database.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.md.database.dao.EmployeeDao
import com.md.database.models.REmployee


@Database(entities = [(REmployee::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}