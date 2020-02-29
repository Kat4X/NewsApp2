package com.kat4x.alyxnews.models.localDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kat4x.alyxnews.models.innerUse.ItemNews

@Entity(tableName = "News")
data class DbNews(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String,
    @ColumnInfo(name = "source")
    var source: Source,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String
) {
    @Entity(tableName = "Source")
    data class Source(
        @ColumnInfo
        var id: String,
        @ColumnInfo
        var name: String
    )
}