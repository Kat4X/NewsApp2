package com.kat4x.alyxnews.models.network


import com.google.gson.annotations.SerializedName
import java.net.URI
import java.util.*

data class ResponseNews(
    @SerializedName("articles")
    var articles: List<Article>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalResults")
    var totalResults: Int?
) {
    data class Article(
        @SerializedName("author")
        var author: String?,
        @SerializedName("content")
        var content: String?,
        @SerializedName("description")
        var description: String?,
        @SerializedName("publishedAt")
        var publishedAt: Date?,
        @SerializedName("source")
        var source: Source?,
        @SerializedName("title")
        var title: String?,
        @SerializedName("url")
        var url: String?,
        @SerializedName("urlToImage")
        var urlToImage: String?
    ) {
        data class Source(
            @SerializedName("id")
            var id: String?,
            @SerializedName("name")
            var name: String?
        )
    }
}