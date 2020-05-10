package com.md.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.md.database.models.REmployee

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM EMP_MASTER")
    fun getAllEmployee(): List<REmployee>

    @Insert
    fun insertEmployee(vararg REmployee: REmployee)
}

