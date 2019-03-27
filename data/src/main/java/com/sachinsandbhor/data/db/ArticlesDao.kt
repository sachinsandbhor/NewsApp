package com.sachinsandbhor.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sachinsandbhor.data.entities.ArticleData
import io.reactivex.Flowable

@Dao
interface ArticlesDao {

    @Query("Select * from news_article")
    fun getAllArticles(): Flowable<List<ArticleData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllArticles(articles: List<ArticleData>)

    @Query("DELETE FROM news_article")
    fun clear()
}