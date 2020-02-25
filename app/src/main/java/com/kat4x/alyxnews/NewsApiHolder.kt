package com.kat4x.alyxnews

import com.kat4x.alyxnews.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiHolder {

    @GET("v2/everything")
    suspend fun fetchNewsAsync(
        @Query("q") q: String? = null,
//        @Query("from") from: String? = null,
        @Query("language") language: String? = null,
        @Query("sortBy") sortBy: String? = null
    ): Response<NewsResponse>

}