package com.example.mathapp.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mathapp.R
import com.example.mathapp.ui.theme.MathAppTheme
import com.example.mathapp.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
    ) {
    LaunchedEffect(Unit) { viewModel.initialize(restartApp) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onAddClick(openScreen) },
                modifier = modifier.padding(16.dp),
                contentColor = Purple40,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {

        var showExitAppDialog by remember { mutableStateOf(false) }
        var showRemoveAccDialog by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding()
            .fillMaxWidth()
            .fillMaxHeight()) {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = { showExitAppDialog = true }) {
                        Icon(Icons.Filled.ExitToApp, "Exit app")
                    }
                    IconButton(onClick = { showRemoveAccDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout),
                            contentDescription = "Remove account",
                            tint = Unspecified
                        )
                    }
                }
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp))


            if (showExitAppDialog) {
                AlertDialog(
                    title = { Text(text = "Sign out?") },
                    text = { Text("Sign out message") },
                    dismissButton = {
                        Button(onClick = { showExitAppDialog = false }) {
                            Text("Cancel")
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.onSignOut()
                            showExitAppDialog = false
                        }) {
                            Text("Sign Out")
                        }
                    },
                    onDismissRequest = { showExitAppDialog = false }
                )
            }

            if (showRemoveAccDialog) {
                AlertDialog(
                    title = { Text(text = "Delete account ?") },
                    text = { Text("Delete Account") },
                    dismissButton = {
                        Button(onClick = { showRemoveAccDialog = false }) {
                            Text(text = "Cancel")
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.onDeleteAccount()
                            showRemoveAccDialog = false
                        }) {
                            Text(text = "Delete")
                        }
                    },
                    onDismissRequest = { showRemoveAccDialog = false }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    MathAppTheme {
        HomeScreen(
            modifier = Modifier,
            restartApp = {},
            openScreen = {},
            )
    }
}