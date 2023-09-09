package com.app.jokeapp.jokeui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.jokeapp.model.JokesModel
import com.app.jokeapp.util.Utils

@Composable
fun JokesListScreen(paddingValues: PaddingValues, viewModel: MainViewModel, modifier: Modifier) {
    val list by viewModel.jokeList.collectAsState()
    LazyColumn(modifier = modifier.padding(paddingValues)) {
        itemsIndexed(
            items = list,
            key = { index: Int, item: JokesModel -> index.toString() + item.joke }
        ) { _, item: JokesModel ->
            JokeItem(modifier = modifier, item = item)
        }

    }

}

@Composable
fun JokeItem(modifier: Modifier, item: JokesModel) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingValues(horizontal = 15.dp, vertical = 8.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            text = item.joke,
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            fontSize = 15.sp,
            color = Color.Black
        )
        Text(
            text = Utils.currentDate(item.time),
            fontSize = 12.sp,
            modifier = modifier.padding(8.dp)
        )
    }
}
