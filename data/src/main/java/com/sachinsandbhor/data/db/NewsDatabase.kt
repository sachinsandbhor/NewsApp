package com.sachinsandbhor.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sachinsandbhor.data.entities.ArticleData

@Database(entities = arrayOf(ArticleData::class), version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}