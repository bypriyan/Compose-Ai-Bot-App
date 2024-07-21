package com.bypriyan.aichatapp.repo

import android.util.Log
import com.bypriyan.todoapp.networkResp.NetworkResponce
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AiRepositry @Inject constructor(val generativeModel: GenerativeModel) {



    suspend fun sendMessage(question:String):Flow<NetworkResponce<String>> = flow<NetworkResponce<String>> {

        val chat = generativeModel.startChat()
        val responce = chat.sendMessage(question)
        emit(NetworkResponce.Success(responce.text.toString()))

    }.catch {
        emit(NetworkResponce.Error(it.message.toString()))
    }

}