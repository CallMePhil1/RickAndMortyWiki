package com.github.callmephil1.rickandmortywiki.ui.compose.search

import com.github.callmephil1.rickandmortywiki.data.model.SearchEntry
import com.github.callmephil1.rickandmortywiki.data.model.SearchInfo
import com.github.callmephil1.rickandmortywiki.data.model.SearchLocation
import com.github.callmephil1.rickandmortywiki.data.model.SearchOrigin
import com.github.callmephil1.rickandmortywiki.data.model.SearchResponse
import com.github.callmephil1.rickandmortywiki.data.repository.RickAndMortyRepository
import com.github.callmephil1.rickandmortywiki.ui.loader.LoaderManager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.util.Date

class SearchViewModelTest {

    lateinit var loaderManager: LoaderManager
    lateinit var rickAndMortyRepository: RickAndMortyRepository
    lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        loaderManager = mockk()
        every { loaderManager.showContentLoader(any(), any()) } just runs

        rickAndMortyRepository = mockk()

        searchViewModel = SearchViewModel(
            loaderManager = loaderManager,
            rickAndMortyRepository = rickAndMortyRepository
        )
    }

    @Test
    fun `GIVEN WHEN search is called THEN search service should be called and update uiState`() = runTest {
        // Arrange
        coEvery { rickAndMortyRepository.search(any()) } returns Result.success(searchResponse)

        // Act
        searchViewModel.search("Rick")

        // Assert
        coVerify { rickAndMortyRepository.search("Rick") }
    }

    companion object {
        val searchResponse = SearchResponse(
            info = SearchInfo(
                count = 5,
                pages = 1,
                next = "",
                prev = ""
            ) ,
            results = listOf(
                SearchEntry(
                    id = 1,
                    name = "Someone Cool",
                    status = "",
                    species = "",
                    type = "",
                    gender = "",
                    origin = SearchOrigin(
                        name = "",
                        url = ""
                    ),
                    location = SearchLocation(
                        name = "",
                        url = ""
                    ),
                    image = "",
                    episode = listOf(),
                    url = "",
                    created = Date.from(Instant.now())
                )
            )
        )
    }
}