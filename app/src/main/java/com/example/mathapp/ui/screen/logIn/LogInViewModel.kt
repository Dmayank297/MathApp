package com.example.mathapp.ui.screen.logIn

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.Credential
import com.example.mathapp.MathAppViewModel
import com.example.mathapp.model.Services.AccountService
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor(
    private val accountService: AccountService
) : MathAppViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onLogInClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.signIn(email.value, password.value)
            openAndPopUp("HomeScreen", "LogInScreen")
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp("SignUpScreen", "LogInScreen")
    }

    fun onSignInWithGoogle(credential: Credential, openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            if(credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                accountService.signInWithGoogle(googleIdTokenCredential.idToken)
                openAndPopUp("HomeScreen", "LogInScreen")

            } else {
                throw IllegalArgumentException("Invalid credential type")
                Log.e("LogInViewModel", "Invalid credential type")
            }
        }

    }
}