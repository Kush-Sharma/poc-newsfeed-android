package com.example.newsfeedapp.domain.repository

import androidx.paging.PagingData
import com.example.newsfeedapp.domain.model.Article
import com.example.newsfeedapp.domain.usecases.news.FetchArticle
import kotlinx.coroutines.flow.Flow
import java.net.URL

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun FetchArticles(): Flow<List<Article>>

    suspend fun FetchArticle(url: String): Article?
}