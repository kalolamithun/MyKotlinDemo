package com.md.mykotlin.ui.main.model

import com.md.database.models.REmployee

data class Data(
    val id: String,
    val employee_name: String,
    val employee_age: String,
    val employee_salary: String,
    val profile_image: String
)

/**
 * Map EMPLOYEE api model to convert EMPLOYEES data entities for store data using room
 */
fun List<Data>.asConvertEmployeeDataModel(): List<REmployee> {
    return map {
        REmployee(
            id = it.id,
            empName = it.employee_name,
            empAge = it.employee_age,
            empSalary = it.employee_salary,
            empProfileImage = it.profile_image
        )
    }
}