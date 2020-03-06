package com.kat4x.alyxnews.repository

import com.kat4x.alyxnews.TypeConverters
import com.kat4x.alyxnews.Webservice
import com.kat4x.alyxnews.models.innerUse.ItemNews
import com.kat4x.alyxnews.models.network.ResponseNews

class NewsRepository(
    private val webservice: Webservice
) : BaseRepository() {
    private suspend fun getNews(q: String, language: String): MutableList<ResponseNews.Article>? {
        return safeApiCall(
            call = {
                webservice.fetchNewsAsync(q, language)
            },
            error = "Error: fetching news"
        )?.articles!!.toMutableList()
    }

    @Synchronized
    suspend fun getNewsItems(): MutableList<ItemNews>{
        return TypeConverters.convertResponseToItem(getNews("technology", "ru")!!.toMutableList())
    }

    private suspend fun getTopNews(county: String, category: String): MutableList<ResponseNews.Article>? {
        return safeApiCall(
            call = {
                webservice.fetchTopNews(county, category)
            },
            error = "Error: fetching news"
        )?.articles!!.toMutableList()
    }

    @Synchronized
    suspend fun getTopNewsItems(): MutableList<ItemNews>{
        return TypeConverters.convertResponseToItem(getTopNews("ru", "business")!!.toMutableList())
    }
}