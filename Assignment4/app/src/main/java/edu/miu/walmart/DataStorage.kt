//package edu.miu.walmart;
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.webkit.CookieManager
//
//
//class DataStorage(context: Context, prefsName: String = "users") {
//    val prefs: SharedPreferences = context.getSharedPreferences("localdata" + prefsName, 0)
//
//    var users: String
//    get() = prefs.getString("users", "")!!
//    set(value) = prefs.edit().putString("users", value).apply()
//}