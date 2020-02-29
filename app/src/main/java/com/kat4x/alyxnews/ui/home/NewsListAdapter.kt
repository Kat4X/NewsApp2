package com.kat4x.alyxnews.ui.home

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kat4x.alyxnews.R
import com.kat4x.alyxnews.models.innerUse.ItemNews
import kotlinx.android.synthetic.main.news_item.view.*

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

        @SuppressLint("SetTextI18n")
        fun bind(item: ItemNews) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.news_title.text = item.title
            itemView.news_descriptions.text = "${item.description}..."
            itemView.news_author.text = item.author
            itemView.news_time.text = item.publishedAt

            if (item.urlToImage != null) {
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(item.urlToImage)
                    .into(itemView.news_image)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ItemNews)
    }
}