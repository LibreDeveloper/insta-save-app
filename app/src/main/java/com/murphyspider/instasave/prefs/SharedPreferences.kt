package com.murphyspider.instasave.prefs

import android.content.Context

class SharedPreferences(context:Context) {
    val prefName = "userPrefs"
    val preferences = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)


    fun getValue(key:String):String?{
        return preferences.getString(key,null)
    }
    fun setValue(key:String,value:String){
        preferences.edit().putString(key,value).apply()
    }
}