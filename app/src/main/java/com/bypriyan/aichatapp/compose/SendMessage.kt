package com.bypriyan.aichatapp.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bypriyan.aichatapp.R

@Composable
fun sendMessage(message: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .imePadding()
            .padding(start = 15.dp, top = 6.dp, end = 15.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        SimpleFilledTextFieldSample(text = text, modifier = Modifier.weight(1f).imePadding()) { message ->
            text = message
        }

        Spacer(modifier = Modifier.width(10.dp))

        IconButton(onClick = {
            if(text.isNotEmpty()){
                message(text.trim()) // Send the message
                text = "" // Clear the text field
            }
        }) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleFilledTextFieldSample(
    text: String,
    modifier: Modifier,
    message: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { newText ->
            message(newText)
        },
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors( // Set your desired background color
            focusedIndicatorColor = Color.Transparent, // Remove focused indicator
            unfocusedIndicatorColor = Color.Transparent // Remove unfocused indicator
        ),
        maxLines = 15,
        shape = RoundedCornerShape(20.dp), // Optional: Change the shape of the TextField
        singleLine = false,
        placeholder = {
            Text(
                "Type a message..",
                color = colorResource(id = R.color.dark_greay)
            )
        } // Optional: Add a placeholder
    )
}
