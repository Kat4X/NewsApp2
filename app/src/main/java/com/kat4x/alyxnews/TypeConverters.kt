package com.kat4x.alyxnews

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import com.kat4x.alyxnews.models.innerUse.ItemNews
import com.kat4x.alyxnews.models.network.ResponseNews
import java.text.SimpleDateFormat

class TypeConverters {

    companion object {

        @SuppressLint("SimpleDateFormat")
        @TypeConverter
        fun convertResponseToItem(article: List<ResponseNews.Article>): MutableList<ItemNews> {
            val reqList: MutableList<ItemNews> = mutableListOf()

            article.forEach {
                val item = ItemNews()
                item.author = it.author.toString()
                item.content = it.content.toString()
                item.description = it.description.toString()
                val sdf = SimpleDateFormat("dd-M-yyyy hh:mm")
                item.publishedAt = it.publishedAt!!.format(sdf)
                item.source.apply {
                    it.source
                }
                item.title = it.title.toString()
                item.urlToImage = it.urlToImage
                item.url = it.url
                reqList.add(item)
            }
            return reqList
        }

    }
}