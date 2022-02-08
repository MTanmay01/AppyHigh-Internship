package com.mtanmay.appyhighinternship.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mtanmay.appyhighinternship.api.Article
import com.mtanmay.appyhighinternship.databinding.NewsItemBinding

class Adapter : PagingDataAdapter<Article, Adapter.ViewHolder>(DIFF_UTIL_CALLBACK) {

    private lateinit var mListener: OnItemClickListener

    companion object {
        val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Article>() {

            override fun areItemsTheSame(oldItem: Article, newItem: Article) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Article, newItem: Article) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    inner class ViewHolder(
        private val binding: NewsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {

            binding.apply {
                newsTitle.text = article.title

                Glide.with(itemView)
                    .load(article.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(newsImage)
            }

            itemView.setOnClickListener {
                mListener.onItemClick(article)
            }

        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(article: Article)
    }

}