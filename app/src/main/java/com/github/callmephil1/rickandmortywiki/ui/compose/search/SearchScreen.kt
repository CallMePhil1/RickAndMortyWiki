package com.github.callmephil1.rickandmortywiki.ui.compose.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.callmephil1.rickandmortywiki.R
import com.github.callmephil1.rickandmortywiki.data.model.SearchEntry
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchEntry(
    entry: SearchEntry,
    onClick: (SearchEntry) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick(entry)
        }
    ) {
        AsyncImage(
            model = entry.image,
            contentDescription = entry.name,
            error = painterResource(R.drawable.ic_launcher_foreground),
            modifier = Modifier.width(120.dp).height(120.dp)
        )
        Text(
            text = entry.name
        )
    }
}

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onEntryClick: (SearchEntry) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(searchText) {
        delay(500)
        viewModel.search(searchText)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(uiState.entries) {
                SearchEntry(
                    entry = it,
                    onClick = onEntryClick
                )
            }
        }
    }
}