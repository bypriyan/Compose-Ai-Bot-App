package com.bypriyan.aichatapp.model

import android.net.Uri

data class ModelMessage(
    val message: String,
    val role:String,
    val uri:Uri?
)
