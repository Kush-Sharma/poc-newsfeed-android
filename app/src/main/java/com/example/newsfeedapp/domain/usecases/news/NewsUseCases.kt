package com.example.newsfeedapp.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val fetchArticles: FetchArticles,
    val fetchArticle: FetchArticle,
)
