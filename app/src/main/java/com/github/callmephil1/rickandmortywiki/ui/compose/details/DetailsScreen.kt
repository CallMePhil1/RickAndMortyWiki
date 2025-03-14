package com.github.callmephil1.rickandmortywiki.ui.compose.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat

val formatter = SimpleDateFormat("MMM dd, yyyy")

@Composable
fun DetailsScreen(
    id: Int,
    viewModel: DetailsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchEntry = uiState.searchEntry

    LaunchedEffect(id) {
        viewModel.fetchInfo(id)
    }

    if (searchEntry == null)
        return

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            AsyncImage(
                model = searchEntry.image,
                contentDescription = searchEntry.name,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(
                    text = "Species: ${searchEntry.species}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Status: ${searchEntry.status}",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(
                    text = "Origin: ${searchEntry.origin.name}",
                    style = MaterialTheme.typography.titleLarge
                )

                if (searchEntry.type.isNotBlank()) {
                    Text(
                        text = "Type: ${searchEntry.type}",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }

        Text(
            text = "Created: ${formatter.format(searchEntry.created)}",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        )
    }
}