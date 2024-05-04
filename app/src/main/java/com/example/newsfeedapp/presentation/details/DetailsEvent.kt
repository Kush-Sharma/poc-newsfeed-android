package com.example.newsfeedapp.presentation.details

sealed class DetailsEvent {
    data object SaveArticle: DetailsEvent()
}