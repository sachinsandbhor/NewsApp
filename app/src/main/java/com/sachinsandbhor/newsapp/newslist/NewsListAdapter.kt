package com.sachinsandbhor.newsapp.newslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachinsandbhor.newsapp.R
import com.sachinsandbhor.newsapp.entities.Article
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    var newsArticles = mutableListOf<Article>()
    companion object {
        private val TAG = NewsListAdapter::class.java.simpleName
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsArticles[position])
    }


    fun updateList(list: List<Article>) {
        if (list.isNotEmpty()) {
            newsArticles.clear()
            newsArticles.addAll(list)
            notifyDataSetChanged()
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(articleItem: Article) {
            with(itemView) {
                header.text = articleItem.description
            }
        }
    }
}