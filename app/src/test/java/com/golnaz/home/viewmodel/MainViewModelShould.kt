package com.golnaz.home.viewmodel

import app.cash.turbine.test
import com.golnaz.home.domain.MainRepository
import com.golnaz.home.fakeItems
import com.golnaz.home.network.model.NetworkResult
import com.golnaz.home.ui.toContent
import com.golnaz.home.ui.viewmodel.MainViewModel
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class MainViewModelShould {

    private val repository: MainRepository = mockk()
    private lateinit var viewModel: MainViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        viewModel = MainViewModel(repository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenItemClicked_shouldNavigateToDetail() {
        runTest {
            viewModel.processAction(MainViewModel.Action.OnItemClicked(1))
            viewModel.eventFlow.test {
                expectMostRecentItem() shouldBe MainViewModel.MainEvent.OpenDetails(1)
            }
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        runTest {

            coEvery { repository.getItemsList() } returns
                    flow {
                        emit(NetworkResult.Fail("Error"))
                    }

            viewModel.processAction(MainViewModel.Action.Init)

            viewModel.state.test {
                expectMostRecentItem().apply {
                    error shouldBe "Error"
                }
            }
        }
    }

    @Test
    fun givenServerResponse_whenFetch_shouldReturnSuccess() {
        runTest {
            coEvery { repository.getItemsList() } returns
                    flow {
                        emit(NetworkResult.Success(fakeItems()))
                    }

            viewModel.processAction(MainViewModel.Action.Init)

            viewModel.state.test {
                expectMostRecentItem().apply {
                    items shouldBe fakeItems().items.toContent()
                    error shouldBe ""
                }
            }
        }
    }

}