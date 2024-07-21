package com.bypriyan.aichatapp.repo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import com.bypriyan.aichatapp.R
import com.bypriyan.aichatapp.model.ModelQuestion
import com.bypriyan.todoapp.networkResp.NetworkResponce
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AiRepositry @Inject constructor(val context: Context, val generativeModel: GenerativeModel) {



    suspend fun sendMessage(modelQuestion: ModelQuestion):Flow<NetworkResponce<String>> = flow<NetworkResponce<String>> {

        val chat = generativeModel.startChat()
        val responce = chat.sendMessage(modelQuestion.question)

        val inputContent = when(modelQuestion.bitmap!=null){
            true -> content {
                uriToBitmap(context, modelQuestion.bitmap)?.let { image(it) }
                text(modelQuestion.question)
            }
            false -> content {
                text(modelQuestion.question)
            }
        }

        var fullResponse = ""
        generativeModel.generateContentStream(inputContent).collect { chunk ->
            fullResponse += chunk.text
            Log.d("content", "sendMessage: $fullResponse")
        }

        emit(NetworkResponce.Success(fullResponse))

//        emit(NetworkResponce.Success(responce.text.toString()))

    }.catch {
        emit(NetworkResponce.Error(it.message.toString()))
    }

    fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}

