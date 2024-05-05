package com.example.newsfeedapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsfeedapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(title = "Welcome",
        description = "Lorem ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding1
    ),
    Page(title = "News Feed",
        description = "Lorem ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding2
    ),
    Page(title = "Sharing News",
        description = "Lorem ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding3
    )
)