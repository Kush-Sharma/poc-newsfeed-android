package com.example.newsfeedapp

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsfeedapp.domain.usecases.appentry.AppEntryUseCases
import com.example.newsfeedapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    init {
        appEntryUseCases.fetchAppEntry().onEach { showHomeScreen ->
            if (showHomeScreen) {
                _startDestination.value = Route.NewsNavigation.route
            } else {
                _startDestination.value = Route.AppStartNavigation.route
            }

            delay(300)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}