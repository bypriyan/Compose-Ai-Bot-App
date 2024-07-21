package com.bypriyan.aichatapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.aichatapp.model.ModelMessage
import com.bypriyan.aichatapp.model.ModelQuestion
import com.bypriyan.aichatapp.repo.AiRepositry
import com.bypriyan.todoapp.networkResp.NetworkResponce
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(val aiRepositry: AiRepositry) : ViewModel() {

    private val _messageList = MutableStateFlow<List<ModelMessage>>(emptyList())
    val messageList: StateFlow<List<ModelMessage>> = _messageList

    private val _status = MutableStateFlow<String>("Online")
    val status: StateFlow<String> = _status

    fun sendMessage(modelQuestion: ModelQuestion) {

        _status.value = "Typing..."

        viewModelScope.launch {
            val newList = _messageList.value.toMutableList()
            newList.add(ModelMessage(modelQuestion.question, "user", modelQuestion.bitmap))
            _messageList.value = newList

            val typeList = _messageList.value.toMutableList()
            typeList.add(ModelMessage("Typing...", "bot", null))
            _messageList.value = typeList

            aiRepositry.sendMessage(modelQuestion = modelQuestion).collect { message ->
                typeList.removeLast()
                _messageList.value = typeList
                when (message) {
                    is NetworkResponce.Success -> {
                        _status.value = "Online"
                        val updatedList = _messageList.value.toMutableList()
                        updatedList.add(ModelMessage(message.data, "bot",null))
                        _messageList.value = updatedList
                        Log.d("collect", "sendMessage: ${message.toString()}")
                    }
                    is NetworkResponce.Error -> {
                        val updatedList = _messageList.value.toMutableList()
                        updatedList.add(ModelMessage(message.message, "bot",null))
                        _messageList.value = updatedList
                    }
                    else -> {}
                }
            }
        }
    }
}
