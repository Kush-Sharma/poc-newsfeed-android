package com.example.newsfeedapp.domain.usecases.news

import com.example.newsfeedapp.data.local.NewsDao
import com.example.newsfeedapp.domain.model.Article
import com.example.newsfeedapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class FetchArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.FetchArticles()
    }

}