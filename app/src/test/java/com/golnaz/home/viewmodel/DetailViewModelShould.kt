package com.golnaz.home.viewmodel

import app.cash.turbine.test
import com.golnaz.home.domain.MainRepository
import com.golnaz.home.fakeItemDetail
import com.golnaz.home.network.model.NetworkResult
import com.golnaz.home.ui.toContent
import com.golnaz.home.ui.viewmodel.DetailViewModel
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
class DetailViewModelShould {

    private val repository: MainRepository = mockk()
    private lateinit var viewModel: DetailViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        viewModel = DetailViewModel(repository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenItemClicked_shouldNavigateToDetail() {
        runTest {
            viewModel.processAction(DetailViewModel.Action.OnBackPressed)
            viewModel.eventFlow.test {
                expectMostRecentItem() shouldBe DetailViewModel.DetailEvent.OnPopBackStack
            }
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        runTest {

            coEvery { repository.getDetailItem("1") } returns
                    flow {
                        emit(NetworkResult.Fail("Error"))
                    }

            viewModel.processAction(DetailViewModel.Action.Init("1"))

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
            coEvery { repository.getDetailItem("1") } returns
                    flow {
                        emit(NetworkResult.Success(fakeItemDetail()))
                    }

            viewModel.processAction(DetailViewModel.Action.Init("1"))

            viewModel.state.test {
                expectMostRecentItem().apply {
                    userDetail shouldBe fakeItemDetail().toContent()
                    error shouldBe ""
                }
            }
        }
    }

}