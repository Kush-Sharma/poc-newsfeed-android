package com.example.newsfeedapp.presentation.details

import com.example.newsfeedapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article): DetailsEvent()

    data object RemoveSideEffect: DetailsEvent()
}