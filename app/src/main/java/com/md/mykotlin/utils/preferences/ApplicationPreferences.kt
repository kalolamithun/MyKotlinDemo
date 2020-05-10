package com.md.mykotlin.utils.preferences

import android.content.Context
import android.content.SharedPreferences

object ApplicationPreferences {
    var sharedPreferences: SharedPreferences? = null
    fun init(context: Context, preferencesName: String = context.packageName){
        sharedPreferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
    }
}