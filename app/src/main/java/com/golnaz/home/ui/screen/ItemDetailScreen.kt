package com.golnaz.home.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.golnaz.home.R
import com.golnaz.home.ui.component.ErrorComponent
import com.golnaz.home.ui.component.ImageComponent
import com.golnaz.home.ui.component.InfoComponent
import com.golnaz.home.ui.viewmodel.DetailViewModel

@Composable
fun ItemDetailScreen(
    id: String,
    viewModel: DetailViewModel,
    popBackStack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.processAction(DetailViewModel.Action.Init(id))
        viewModel.eventFlow.collect {
            when (it) {
                DetailViewModel.DetailEvent.OnPopBackStack -> {
                    popBackStack()
                }
            }
        }
    }
    DetailContent(state, viewModel::processAction)
}

@Composable
fun DetailContent(
    state: DetailViewModel.DetailViewState,
    processAction: (DetailViewModel.Action) -> Unit
) {
    Column(modifier = Modifier.padding(24.dp)) {
        Image(
            modifier = Modifier
                .clickable {
                    processAction(DetailViewModel.Action.OnBackPressed)
                },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back",
            alignment = Alignment.TopStart,
        )

        state.userDetail?.let { userDetail ->
            Column(modifier = Modifier.padding(24.dp)) {

                ImageComponent(url = userDetail.url)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = userDetail.propertyType, fontWeight = FontWeight(500))
                Text(text = userDetail.priceValue, fontWeight = FontWeight(600))

                Spacer(modifier = Modifier.height(8.dp))

                InfoComponent(title = stringResource(R.string.professional), value = userDetail.professional)
                InfoComponent(title = stringResource(R.string.address), value = userDetail.city)
                InfoComponent(title = stringResource(R.string.rooms), value = userDetail.rooms)
                InfoComponent(title = stringResource(R.string.bedrooms), value = userDetail.bedrooms)
                InfoComponent(title = stringResource(R.string.area), value = userDetail.area)
            }
        } ?: ErrorComponent(state.error)
    }
}
