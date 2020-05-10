package com.md.mykotlin.ui.main.api

import com.md.mykotlin.ui.main.model.Employee
import retrofit2.Call
import retrofit2.http.GET

interface HomeInterceptor {
    @GET("v1/employees")
    fun getEmpAPi(): Call<Employee>
}