package com.example.newsfeedapp.domain.usecases.news

import androidx.paging.PagingData
import com.example.newsfeedapp.data.remote.NewsPagingSource
import com.example.newsfeedapp.domain.model.Article
import com.example.newsfeedapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }

}