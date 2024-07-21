package com.bypriyan.aichatapp.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bypriyan.aichatapp.R
import com.bypriyan.aichatapp.viewModel.ChatViewModel

@Composable
fun topBarProfile(chatViewModel: ChatViewModel) {

    val status by chatViewModel.status.collectAsState()

    ConstraintLayout {
        val (profileImg, name, statusText) = createRefs()
        Box(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(profileImg) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.bot_logo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(color = colorResource(id = R.color.green))
                    .align(Alignment.BottomEnd)
            )
        }

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(name) {
                    start.linkTo(profileImg.end, margin = 20.dp)
                    top.linkTo(profileImg.top)
                    bottom.linkTo(profileImg.bottom)
                },
            verticalArrangement = Arrangement.spacedBy(0.dp) // Set spacing to 0.dp
        ) {
            Text(
                text = "Priyanshu Prajapati",
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily(Font(R.font.semibold))
            )

            Text(
                text = status,
                color = colorResource(id = R.color.green),
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily(Font(R.font.semibold))
            )
        }
    }
}
