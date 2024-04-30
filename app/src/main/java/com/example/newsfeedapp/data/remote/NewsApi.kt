package com.example.newsfeedapp.data.remote

import com.example.newsfeedapp.data.remote.dto.NewsResponse
import com.example.newsfeedapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}