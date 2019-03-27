package com.sachinsandbhor.newsapp.newslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachinsandbhor.newsapp.R
import com.sachinsandbhor.newsapp.entities.Article
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    var newsArticles = mutableListOf<Article>()

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

    fun updateList(articlesList: List<Article>) {
        if (articlesList.isNotEmpty()) {
            newsArticles.clear()
            newsArticles.addAll(articlesList)
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