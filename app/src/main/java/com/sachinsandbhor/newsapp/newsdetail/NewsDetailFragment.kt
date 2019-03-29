package com.sachinsandbhor.newsapp.newsdetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sachinsandbhor.newsapp.databinding.FragmentNewsDetailBinding
import com.sachinsandbhor.newsapp.entities.Article
import kotlinx.android.synthetic.main.fragment_news_detail.*


class NewsDetailFragment : Fragment() {

    val args: NewsDetailFragmentArgs by navArgs()
    lateinit var newsDetailBinding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsDetailBinding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return newsDetailBinding.getRoot()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article: Article = args.article
        newsDetailBinding.article = article
    }

}

