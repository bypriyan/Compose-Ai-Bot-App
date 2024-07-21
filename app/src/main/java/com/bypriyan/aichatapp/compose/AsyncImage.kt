package com.bypriyan.aichatapp.compose

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bypriyan.aichatapp.R

@Composable
fun loadImage(uri: Uri, dp: Dp, modifier: Modifier, cancelClicked:()->Unit){

    Box(modifier = Modifier.fillMaxWidth()){
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp)
                .wrapContentSize(),
        ){
            AsyncImage(
                model = uri,
                contentDescription = "Image",
                modifier = modifier
                    .size(dp)
                    .background(color = colorResource(id = R.color.transparent))
                    .clip(RoundedCornerShape(10.dp)), // Add rounded corners
                contentScale = ContentScale.Crop // Adjust scaling as needed
            )

            IconButton(onClick = {
                cancelClicked()
            }, modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier
                        .size(20.dp)
                        .background(
                            color = colorResource(id = R.color.bg),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(3.dp)

                )
            }

        }
    }



}