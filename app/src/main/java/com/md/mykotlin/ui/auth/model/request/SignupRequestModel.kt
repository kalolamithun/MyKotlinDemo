package com.md.mykotlin.ui.auth.model.request

import com.md.mykotlin.utils.Constants

class SignupRequestModel {

    lateinit var name: String

    lateinit var email: String

    var dialCode = DialCode()

    lateinit var mobileNumber: String

    lateinit var password: String

    var platform = Constants.PLATFORM

    lateinit var deviceToken: String

    class DialCode {

        lateinit var countryName: String

        lateinit var countryDialCode: String

        lateinit var countryCode: String
    }
}