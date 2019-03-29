package com.sachinsandbhor.newsapp.entities

import java.io.Serializable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import androidx.databinding.BindingAdapter



data class Article(
    var author: String? = null,
    var content: String? = null,
    var description: String? = null,
    var publishedAt: String? = null,
    var title: String? = null,
    var url: String? = null,
    var urlToImage: String? = null
): Serializable

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .into(view)
}