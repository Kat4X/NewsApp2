package com.kat4x.alyxnews.ui.home

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kat4x.alyxnews.R
import com.kat4x.alyxnews.models.innerUse.ItemNews
import kotlinx.android.synthetic.main.news_item.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class NewsListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemNews>() {

        override fun areItemsTheSame(oldItem: ItemNews, newItem: ItemNews): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ItemNews, newItem: ItemNews): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return NewsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsItemViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ItemNews>) {
        differ.submitList(list)
    }

    class NewsItemViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(item: ItemNews) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.news_title.text = item.title
            if (item.description.isNotEmpty()) {
                itemView.news_descriptions.text = "${item.description}..."
            }

            if (item.author != "null") {
                itemView.news_author.visibility = View.VISIBLE
                itemView.news_author.text = item.author
            }

            val sdf = SimpleDateFormat("d MMMM hh:mm")
//            val currDate = System.currentTimeMillis()
//            val timeAgo = currDate - item.publishedAt!!.time
//            Log.d("CAL", "${item.publishedAt!!} and ${sdf.format(currDate)} = $timeAgo")
//            itemView.news_time.text = sdf.format(item.publishedAt!!)
            itemView.news_time.text = sdf.format(item.publishedAt!!)

            if (item.urlToImage!!.isNotEmpty()) {
                val imageUri = Uri.parse(item.urlToImage)
                itemView.news_image.setImageURI(imageUri, context!!)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ItemNews)
    }
}
