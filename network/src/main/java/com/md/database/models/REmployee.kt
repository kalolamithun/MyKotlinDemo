package com.md.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EMP_MASTER", primaryKeys = ["id"])
data class REmployee(
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "employee_name") var empName: String,
    @ColumnInfo(name = "employee_age") var empAge: String,
    @ColumnInfo(name = "employee_salary") var empSalary: String,
    @ColumnInfo(name = "profile_image") var empProfileImage: String
)


