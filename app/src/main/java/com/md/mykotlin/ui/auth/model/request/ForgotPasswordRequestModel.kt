package com.md.mykotlin.ui.auth.model.request

import com.md.mykotlin.utils.Constants

class ForgotPasswordRequestModel {

    lateinit var email: String

    var platform = Constants.PLATFORM

    lateinit var deviceToken: String

}