package com.bypriyan.aichatapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bypriyan.aichatapp.R
import com.bypriyan.aichatapp.model.ModelMessage

@Composable
fun messageList(messageList:List<ModelMessage>){

    if(messageList.isEmpty()){
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            lottieAnimation(R.raw.hello_lottie)
        }
    }else{
        LazyColumn(modifier = Modifier.fillMaxSize(),
            reverseLayout = true){
            items(messageList.reversed()){
                messageRow(it)
            }
        }
    }

}

@Composable
fun messageRow(modelMessage: ModelMessage) {
    val isBot = modelMessage.role=="bot"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Box(modifier = Modifier
                .align(
                    if (isBot) Alignment.BottomStart else Alignment.BottomEnd
                )
                .padding(
                    start = if (isBot) 8.dp else 80.dp,
                    end = if (isBot) 80.dp else 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
                .background(
                    color = colorResource(id = if (isBot) R.color.limeGreay else R.color.blue),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp)){
                Text(
                    text = modelMessage.message,
                    color = colorResource(id = if(isBot)R.color.black else R.color.white),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W500
                )
            }

        }

    }

}