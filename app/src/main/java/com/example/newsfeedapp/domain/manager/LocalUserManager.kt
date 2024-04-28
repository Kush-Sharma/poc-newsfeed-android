package com.example.newsfeedapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveAppEntry()

    fun fetchAppEntry(): Flow<Boolean>

}