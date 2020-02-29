package com.kat4x.alyxnews

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiService {

    companion object {
        private val interceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder().addQueryParameter("apiKey", App.BASE_API_KEY).build()

            val request= chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }

        private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder().client(apiClient)
                .baseUrl(App.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val serviceApi: Webservice = getRetrofit().create(Webservice::class.java)
    }
}