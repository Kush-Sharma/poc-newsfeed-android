package com.example.newsfeedapp.domain.usecases.appentry

import com.example.newsfeedapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}