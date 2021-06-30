package com.example.moviecatalogue.ui.movie

import com.example.moviecatalogue.data.Content
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun init() {
        movieViewModel = MovieViewModel()
    }

    @Test
    fun getContent() {
        val dummyData = "[{\n" +
                "    \"id\": \"1\",\n" +
                "    \"title\": \"A Star Is Born\",\n" +
                "    \"description\": \"Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.\",\n" +
                "    \"releaseDate\": \"05/10/2018\",\n" +
                "    \"imagePath\": \"poster_a_start_is_born.jpg\"\n" +
                "  }]"

        val content = Content(
            id = 1,
            title = "A Star Is Born",
            description = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            releaseDate = "05/10/2018",
            imagePath = "poster_a_start_is_born.jpg"
        )

        val actualResult = mutableListOf<Content>()
        actualResult.add(content)

        movieViewModel.getContent(dummyData)

        TestCase.assertEquals(actualResult, movieViewModel.content)
    }
}