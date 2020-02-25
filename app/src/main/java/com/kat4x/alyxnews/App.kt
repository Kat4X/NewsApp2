package com.kat4x.alyxnews

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        sp = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val BASE_API_KEY = "daac43edbefc4a3c9776b9bd48eb3a84"
        private const val APP_PREFERENCES = "settings"
        private const val URL: String = "URL"
        private lateinit var sp: SharedPreferences

        fun setUrl(url: String) {
            sp.edit().putString(URL, url).apply()
        }

        fun getUrl(): String = sp.getString(URL, "URL").toString()


    }
}