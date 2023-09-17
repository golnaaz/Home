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

class DetailViewModel(
    private val repository: MainRepository
) : ViewModel() {
    private val eventChannel = Channel<DetailEvent>(Channel.BUFFERED)
    private val stateFlow = MutableStateFlow(DetailViewState())

    val eventFlow = eventChannel.receiveAsFlow()
    val state: StateFlow<DetailViewState> = stateFlow

    fun processAction(action: Action) {
        when (action) {
            is Action.Init -> {
                initDetails(action.id)
            }

            Action.OnBackPressed -> {
                eventChannel.trySend(DetailEvent.OnPopBackStack)
            }
        }
    }

    private fun initDetails(id: String) {
        viewModelScope.launch {
            repository.getDetailItem(id).collect { result ->
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
                                userDetail = result.value.toContent(),
                                error = "",
                            )
                        }
                    }
                }
            }

        }
    }

    sealed class Action {
        data class Init(val id: String) : Action()
        object OnBackPressed : Action()
    }

    sealed class DetailEvent {
        object OnPopBackStack : DetailEvent()
    }

    data class DetailViewState(
        val userDetail: ItemUiModel? = null,
        val error: String = "",
    )
}