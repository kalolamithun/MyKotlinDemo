package com.md.mykotlin.ui.main.ui.home

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.md.database.config.AppDatabase
import com.md.database.dao.EmployeeDao
import com.md.database.models.REmployee
import com.md.mykotlin.KotlinDemo
import com.md.mykotlin.base.BaseViewModel
import com.md.mykotlin.ui.main.api.HomeInterceptor
import com.md.mykotlin.ui.main.model.Data
import com.md.mykotlin.ui.main.model.Employee
import com.md.mykotlin.ui.main.model.asConvertEmployeeDataModel
import com.md.mykotlin.utils.LogUtil
import com.md.network.retrofit.RetrofitClientFactory
import kotlinx.coroutines.newFixedThreadPoolContext
import retrofit2.Call
import retrofit2.Response

class HomeViewModel(application: Application) : BaseViewModel(application) {
    companion object {
        public const val TAG: String = "HomeViewModel"
    }

    private var employeeDao: EmployeeDao = KotlinDemo.database!!.employeeDao()
    private var empData: List<REmployee> = emptyList()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var employees = MutableLiveData<List<REmployee>>()

    fun getEmployeeData(): LiveData<List<REmployee>> {
        return employees
    }

    fun apiGetEmployee() {
        AsyncTask.execute {
            empData = employeeDao.getAllEmployee()
            if (empData.isNotEmpty()) {
                Log.i(TAG, "<<< Get Data From Local DB")
                employees.postValue(empData)
                return@execute
            }
            return@execute
        }

        if (empData.isNotEmpty()) {
            return
        }

        isLoading.value = true
        RetrofitClientFactory.getInstance(HomeInterceptor::class.java)
            .getEmpAPi()
            .enqueue(object : retrofit2.Callback<Employee> {
                override fun onFailure(call: Call<Employee>, t: Throwable) {
                    isLoading.value = false
                    Log.i(
                        TAG,
                        "Error :: " + t.localizedMessage
                    )
                }

                override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                    isLoading.value = false
                    if (response.code() == 200) {
                        val responseModel = response.body()
                        if (responseModel?.data?.size!! > 0) {
                            Log.i(TAG, "<<< Get Data From API >>  Local DB")
                            AsyncTask.execute {
                                val responseData = responseModel.data.asConvertEmployeeDataModel()
                                responseData.forEach { rEmployee: REmployee ->
                                    employeeDao.insertEmployee(rEmployee)
                                }
                                employees.postValue(responseData)
                            }
                        }
                    } else {
                        Log.i(TAG, "ERROR > Data")
                    }
                }
            })
    }

}