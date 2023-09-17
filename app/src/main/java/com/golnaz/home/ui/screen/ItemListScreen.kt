package com.golnaz.home.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.golnaz.home.R
import com.golnaz.home.ui.component.ErrorComponent
import com.golnaz.home.ui.component.ImageComponent
import com.golnaz.home.ui.model.ItemUiModel
import com.golnaz.home.ui.viewmodel.MainViewModel

@Composable
fun ItemListScreen(
    viewModel: MainViewModel,
    navigateToDetail: (Int) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.processAction(MainViewModel.Action.Init)
        viewModel.eventFlow.collect {
            when (it) {
                is MainViewModel.MainEvent.OpenDetails ->
                    navigateToDetail(
                        it.id
                    )
            }
        }
    }
    MainListContent(state, viewModel::processAction)
}

@Composable
fun MainListContent(
    state: MainViewModel.MainViewState,
    processAction: (MainViewModel.Action) -> Unit,
) {
    Column(
        modifier = Modifier.padding(
            vertical = 12.dp,
            horizontal = 24.dp,
        )
    ) {
        LazyColumn {
            items(state.items) { item ->
                ItemContent(item, processAction)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        ErrorComponent(state.error)
    }
}

@Composable
fun ItemContent(item: ItemUiModel, processAction: (MainViewModel.Action) -> Unit) {
    Card(
        modifier = Modifier.clickable {
            processAction(MainViewModel.Action.OnItemClicked(item.id))
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            ImageComponent(item.url)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.propertyType, fontWeight = FontWeight(400))
                    Text(text = item.city, fontWeight = FontWeight(200))
                }
                Text(
                    text = item.priceValue,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight(700),
                    color = Color.Black,
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemContentPreview() {
    ItemContent(
        item = ItemUiModel(
            bedrooms = "4",
            city = "Villers-sur-Mer",
            id = 1,
            area = "250",
            url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            priceValue = "140000",
            professional = "GSL EXPLORE",
            propertyType = "Maison - Villa",
            offerType = 1,
            rooms = "8"
        ),
        processAction = {}
    )
}
