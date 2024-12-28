package com.example.mathapp.ui.screen.home

import com.example.mathapp.MathAppViewModel
import com.example.mathapp.SplashScreen
import com.example.mathapp.model.Services.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService
): MathAppViewModel() {

    fun initialize(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(SplashScreen)
            }
        }
    }
    fun onSignOut() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun onDeleteAccount() {
        launchCatching {
            accountService.deleteAccount()
        }
    }

    fun onAddClick(openScreen: (String) -> Unit) {
        openScreen("HomeContentScreen")
    }
}