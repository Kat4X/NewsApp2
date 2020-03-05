package com.kat4x.alyxnews.models.innerUse

import java.net.URI
import java.util.*

data class ItemNews(
    var author: String = "",
    var content: String = "",
    var description: String = "",
    var publishedAt: Date? = null,
    var source: Source = Source(),
    var title: String = "",
    var url: String? = null,
    var urlToImage: String? = null
) {
    data class Source(
        var id: String = "",
        var name: String = ""
    )
}