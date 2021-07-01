package com.example.moviecatalogue.ui.tvshow

import com.example.moviecatalogue.data.Content
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun init() {
        tvShowViewModel = TvShowViewModel()
    }

    @Test
    fun getContent() {
        val dummyData = "[{\n" +
                "    \"id\": \"1\",\n" +
                "    \"title\": \"Arrow\",\n" +
                "    \"description\": \"Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.\",\n" +
                "    \"releaseDate\": \"10/10/2012\",\n" +
                "    \"imagePath\": \"poster_arrow.jpg\"\n" +
                "  }]"

        val content = Content(
            id = 1,
            title = "Arrow",
            description = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            releaseDate = "10/10/2012",
            imagePath = "poster_arrow.jpg"
        )

        val actualResult = mutableListOf<Content>()
        actualResult.add(content)

        tvShowViewModel.getContent(dummyData)
        TestCase.assertNotNull(tvShowViewModel.content)
        TestCase.assertEquals(actualResult, tvShowViewModel.content)
    }
}