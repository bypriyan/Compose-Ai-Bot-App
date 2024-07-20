package com.bypriyan.aichatapp.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.bypriyan.aichatapp.R
import com.bypriyan.aichatapp.viewModel.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(chatViewModel: ChatViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.white),
                    titleContentColor = colorResource(id = R.color.black),
                ),
                title = {
                    Column {
                        topBarProfile()
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ){ innerpading ->
        Log.d("TAG", "homeScreen: $innerpading")

        Column(modifier = Modifier
            .padding(innerpading)
            .fillMaxSize()
            .background(color = colorResource(id = R.color.greay))) {

            Column(modifier = Modifier
                .background(color = colorResource(id = R.color.blue))
                .weight(1f)
                ) {

            }

            sendMessage(){message->
                chatViewModel.sendMessage(message)
            }
        }
    }

}



