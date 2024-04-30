package com.example.newsfeedapp.data.remote.dto

import com.example.newsfeedapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)