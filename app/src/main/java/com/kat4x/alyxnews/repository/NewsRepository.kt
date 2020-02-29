package com.kat4x.alyxnews.repository

import com.kat4x.alyxnews.TypeConverters
import com.kat4x.alyxnews.Webservice
import com.kat4x.alyxnews.models.innerUse.ItemNews
import com.kat4x.alyxnews.models.network.ResponseNews

class NewsRepository(
    private val webservice: Webservice
) : BaseRepository() {
    suspend fun getNews(q: String, language: String): MutableList<ResponseNews.Article>? {
        return safeApiCall(
            call = {
                webservice.fetchNewsAsync(q, language)
            },
            error = "Error: fetching news"
        )?.articles!!.toMutableList()
    }

    suspend fun getNewsItems(/*t: List<ResponseNews.Article>*/): MutableList<ItemNews>{
        return TypeConverters.convertResponseToItem(getNews("technology", "ru")!!.toMutableList())
    }
}