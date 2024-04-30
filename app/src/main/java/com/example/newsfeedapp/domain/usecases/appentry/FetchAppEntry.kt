package com.example.newsfeedapp.domain.usecases.appentry

import com.example.newsfeedapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class FetchAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.fetchAppEntry()
    }
}