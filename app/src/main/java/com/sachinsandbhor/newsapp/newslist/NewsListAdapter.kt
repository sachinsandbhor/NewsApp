package com.sachinsandbhor.newsapp.newslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachinsandbhor.newsapp.R
import com.sachinsandbhor.newsapp.entities.Article
import com.sachinsandbhor.newsapp.utils.DateUtil.Companion.formatDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter(val onClickListener: (article: Article, view: View) -> Unit) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    var newsArticles = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsArticles[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{ onClickListener(item, holder.itemView) }
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
                description.text = articleItem.title
                Picasso.get().load(articleItem.urlToImage).placeholder(R.mipmap.ic_launcher).into(newsimage)
                val publishDate = formatDate(articleItem.publishedAt!!)
                datetextview.text = publishDate
            }
        }
    }
}