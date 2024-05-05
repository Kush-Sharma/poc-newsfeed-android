package com.example.newsfeedapp.domain.usecases.news

import com.example.newsfeedapp.data.local.NewsDao
import com.example.newsfeedapp.domain.model.Article
import com.example.newsfeedapp.domain.repository.NewsRepository

class FetchArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.FetchArticle(url = url)
    }

}