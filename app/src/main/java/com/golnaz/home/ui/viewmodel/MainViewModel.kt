package com.golnaz.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.golnaz.home.domain.MainRepository
import com.golnaz.home.network.model.NetworkResult
import com.golnaz.home.ui.model.ItemUiModel
import com.golnaz.home.ui.toContent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {
    private val eventChannel = Channel<MainEvent>(Channel.BUFFERED)
    private val stateFlow = MutableStateFlow(MainViewState())

    val eventFlow = eventChannel.receiveAsFlow()
    val state: StateFlow<MainViewState> = stateFlow

    fun processAction(action: Action) {
        when (action) {
            Action.Init -> {
                initMainList()
            }

            is Action.OnItemClicked ->
                eventChannel.trySend(MainEvent.OpenDetails(action.id))

            else -> {
                //no op
            }
        }
    }

    private fun initMainList() {
        viewModelScope.launch {
            repository.getItemsList().collect { result ->
                when (result) {
                    is NetworkResult.Fail -> {
                        stateFlow.update {
                            it.copy(
                                error = result.error
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        stateFlow.update {
                            it.copy(
                                items = result.value.items.toContent(),
                                error = "",
                            )
                        }
                    }
                }
            }

        }
    }

    sealed class Action {
        object Init : Action()
        data class OnItemClicked(val id: Int) : Action()
    }

    sealed class MainEvent {
        data class OpenDetails(val id: Int) : MainEvent()
    }

    data class MainViewState(
        val items: ArrayList<ItemUiModel> = arrayListOf(),
        val error: String = "",
    )
}