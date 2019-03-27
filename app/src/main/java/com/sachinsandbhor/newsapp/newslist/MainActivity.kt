package com.sachinsandbhor.newsapp.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sachinsandbhor.newsapp.R
import com.sachinsandbhor.newsapp.entities.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val newsListViewModel: NewsListViewModel by viewModel()
    lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        newsListAdapter = NewsListAdapter()
        news_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        news_list.adapter = NewsListAdapter()
        newsListViewModel.fetchNews()
    }

    override fun onStart() {
        super.onStart()
        newsListViewModel.getNewsLiveData().observe(this, Observer {
          when(it?.responseType) {
              Status.ERROR -> {
                    Toast.makeText(this, it.error?.message, Toast.LENGTH_LONG).show()
              }
              Status.LOADING -> {
                  progressBar.visibility = View.INVISIBLE
              }
              Status.SUCCESS -> {
                  progressBar.visibility = View.INVISIBLE
                  news_list.visibility = View.VISIBLE
              }
          }
            it?.data?.let{ response ->
                newsListAdapter.updateList(response.articles)
            }
        })
    }
}
