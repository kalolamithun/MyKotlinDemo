package com.md.mykotlin.ui.auth.model.response

import com.md.mykotlin.ui.auth.model.response.DialCode

data class UserModel(
    val _id: String,
    val name: String,
    val emailId: String,
    val dialCode: DialCode?,
    val mobileNumber: Long,
    val accessToken: String
)