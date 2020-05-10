package com.md.mykotlin.ui.auth.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.md.mykotlin.base.BaseViewModel
import com.md.mykotlin.ui.auth.api.NetworkInterceptor
import com.md.mykotlin.ui.auth.model.request.LoginRequestModel
import com.md.mykotlin.ui.auth.model.response.UserModel
import com.md.network.retrofit.RetrofitClientFactory
import retrofit2.Call
import retrofit2.Response

class LoginViewModel(application: Application) : BaseViewModel(application) {

    val loginRequestModel: LoginRequestModel = LoginRequestModel()

    init {
        loginRequestModel.deviceToken = uniqueId
    }

    var userData = MutableLiveData<UserModel>()

    fun getUserData(): LiveData<UserModel> {
        return userData
    }

    fun apiCallLogin() {
        isLoading.value = true

        // val requestModel = BaseRequestModel("login",Constants.PLATFORM,loginRequestModel)

        RetrofitClientFactory.getInstance(NetworkInterceptor::class.java)
            .callLoginApi(loginRequestModel)
            .enqueue(object : retrofit2.Callback<UserModel> {

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    isLoading.value = false
                }

                override fun onResponse(
                    call: Call<UserModel>,
                    response: Response<UserModel>
                ) {
                    isLoading.value = false
                    val responseModel = response.body()
                    userData.value = responseModel

//                    if (response.body()?.status == 1) {
//                        val responseModel = response.body()?.getResponseModel(UserModel::class.java)
//                        userData.value = responseModel
//                        // set is isUserLogin into Shared Preference after successful login
//                        // PreferenceConstant.isUserLogin.putBoolean(true)
//                    } else {
//                        response.body()?.message?.let { LogUtil.e("LOGIN", it) }
//                    }
                }
            })
    }
}