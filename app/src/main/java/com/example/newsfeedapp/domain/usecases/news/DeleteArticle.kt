package com.example.newsfeedapp.domain.usecases.news

import com.example.newsfeedapp.data.local.NewsDao
import com.example.newsfeedapp.domain.model.Article
import com.example.newsfeedapp.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }

}