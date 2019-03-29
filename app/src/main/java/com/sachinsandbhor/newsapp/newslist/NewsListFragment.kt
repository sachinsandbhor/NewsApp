package com.sachinsandbhor.newsapp.newslist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sachinsandbhor.newsapp.R
import com.sachinsandbhor.newsapp.entities.Article
import com.sachinsandbhor.newsapp.entities.Status
import com.sachinsandbhor.newsapp.newsdetail.NewsDetailFragment
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment() {

    private val newsListViewModel: NewsListViewModel by viewModel()
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsListViewModel.getNewsLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    Toast.makeText(context, it.error?.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    progressBar.visibility = View.INVISIBLE
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.INVISIBLE
                    news_list.visibility = View.VISIBLE
                }
            }
            it?.data?.let { response ->
                newsListAdapter.updateList(response.articles)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        newsListAdapter = NewsListAdapter() { article: Article, view: View ->
            val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment2(article)
            Navigation.findNavController(view).navigate(action)
        }
        view.news_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        view.news_list.setHasFixedSize(true)
        view.news_list.adapter = newsListAdapter
        newsListViewModel.fetchNews()
    }


}
