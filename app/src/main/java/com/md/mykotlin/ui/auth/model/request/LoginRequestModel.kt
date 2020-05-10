package com.md.mykotlin.ui.auth.model.request

import com.md.mykotlin.utils.Constants

class LoginRequestModel {

    lateinit var emailId: String

    lateinit var password: String

    var deviceType = Constants.PLATFORM

    lateinit var deviceToken: String

}