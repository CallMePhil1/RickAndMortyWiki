package com.github.callmephil1.rickandmortywiki.ui.compose.details

import com.github.callmephil1.rickandmortywiki.data.model.SearchEntry
import com.github.callmephil1.rickandmortywiki.data.model.SearchLocation
import com.github.callmephil1.rickandmortywiki.data.model.SearchOrigin
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

class DetailsViewModelTest {
    lateinit var loaderManager: LoaderManager
    lateinit var rickAndMortyRepository: RickAndMortyRepository
    lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setup() {
        loaderManager = mockk()
        every { loaderManager.showContentLoader(any(), any()) } just runs

        rickAndMortyRepository = mockk()

        detailsViewModel = DetailsViewModel(
            loaderManager = loaderManager,
            rickAndMortyRepository = rickAndMortyRepository
        )
    }

    @Test
    fun `GIVEN WHEN search is called THEN search service should be called and update uiState`() = runTest {
        // Arrange
        coEvery { rickAndMortyRepository.fetchInfo(any()) } returns Result.success(searchEntry)

        // Act
        detailsViewModel.fetchInfo(1)

        // Assert
        coVerify { rickAndMortyRepository.fetchInfo(1) }
    }

    companion object {
        val searchEntry = SearchEntry(
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
    }
}