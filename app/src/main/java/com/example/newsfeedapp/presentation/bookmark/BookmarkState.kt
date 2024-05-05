package com.example.newsfeedapp.presentation.bookmark

import com.example.newsfeedapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
