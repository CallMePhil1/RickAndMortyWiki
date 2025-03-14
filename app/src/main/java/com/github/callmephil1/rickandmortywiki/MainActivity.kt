package com.github.callmephil1.rickandmortywiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.github.callmephil1.rickandmortywiki.ui.compose.details.DetailsScreen
import com.github.callmephil1.rickandmortywiki.ui.compose.loader.Loader
import com.github.callmephil1.rickandmortywiki.ui.compose.search.SearchScreen
import com.github.callmephil1.rickandmortywiki.ui.loader.LoaderManager
import com.github.callmephil1.rickandmortywiki.ui.navigation.Destination
import com.github.callmephil1.rickandmortywiki.ui.navigation.NavigationHelper
import com.github.callmephil1.rickandmortywiki.ui.theme.RickAndMortyWikiTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            RickAndMortyWikiTheme {
                KoinAndroidContext {
                    val loaderManager = koinInject<LoaderManager>()
                    val loaderUiState by loaderManager.uiState.collectAsState()
                    val navController = rememberNavController()
                    val navigationHelper = koinInject<NavigationHelper>()
                    val navigationState by navigationHelper.navigationFlow.collectAsState()
                    var title by remember { mutableStateOf("") }
                    var showBackArrow by remember { mutableStateOf(false) }

                    LaunchedEffect(navigationState) {
                        if (navigationState == null) {
                            return@LaunchedEffect
                        }
                        navController.navigate(navigationState!!)
                    }

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = title)
                                },
                                navigationIcon = {
                                    if (showBackArrow) {
                                        IconButton(
                                            onClick = { navController.popBackStack() }
                                        ) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                }
                            )
                        },
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Destination.Search,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable<Destination.Search> {
                                title = Destination.Search.title
                                showBackArrow = Destination.Search.showBackArrow
                                SearchScreen {
                                    navController.navigate(
                                        Destination.Details(
                                            id = it.id,
                                            title = it.name
                                        )
                                    )
                                }
                            }

                            composable<Destination.Details> { backStackEntry ->
                                val details = backStackEntry.toRoute<Destination.Details>()
                                title = details.title
                                showBackArrow = details.showBackArrow
                                DetailsScreen(
                                    id = details.id
                                )
                            }
                        }

                        if (!loaderUiState.showFullscreenLoader and loaderUiState.showContentLoader) {
                            Loader(loaderUiState.loaderAlpha)
                        }
                    }

                    if (loaderUiState.showFullscreenLoader) {
                        Loader(loaderUiState.loaderAlpha)
                    }
                }
            }
        }
    }
}
