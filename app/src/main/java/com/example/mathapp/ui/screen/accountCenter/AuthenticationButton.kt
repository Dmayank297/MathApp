package com.example.mathapp.ui.screen.accountCenter

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.example.mathapp.R
import kotlinx.coroutines.launch

@Composable
fun AuthenticationButton(
    onGetCredentialResponse: (Credential) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val credentialManager = CredentialManager.create(context)

    IconButton(onClick = {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {
            try {
                val result = credentialManager.getCredential(
                    context = context,
                    request = request
                )
                onGetCredentialResponse(result.credential)
            } catch (e: GetCredentialException) {
                Log.d("GetCredentialException", e.localizedMessage.orEmpty())
            }
        }
    }) {
        Icon(
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp),
            painter = painterResource(R.drawable.google),
            contentDescription = "Google_logo",
            tint = Color.Unspecified
        )
    }

}