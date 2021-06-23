package com.example.moviecatalogue.ui.detail

import com.example.moviecatalogue.data.Content
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun init() {
        detailViewModel = DetailViewModel()
    }

    @Test
    fun getContent() {
        val dummyData = "[{\n" +
                "    \"id\": \"1\",\n" +
                "    \"title\": \"A Star Is Born\",\n" +
                "    \"description\": \"Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.\",\n" +
                "    \"releaseDate\": \"05/10/2018\",\n" +
                "    \"imagePath\": \"poster_a_start_is_born.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"2\",\n" +
                "    \"title\": \"Alita: Battle Angel\",\n" +
                "    \"description\": \"When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.\",\n" +
                "    \"releaseDate\": \"14/02/2019\",\n" +
                "    \"imagePath\": \"poster_alita.jpg\"\n" +
                "  }]"

        val actualResult = Content(
            id = 1,
            title = "A Star Is Born",
            description = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            releaseDate = "05/10/2018",
            imagePath = "poster_a_start_is_born.jpg"
        )

        detailViewModel.getContent(dummyData, 1)
        TestCase.assertEquals(actualResult, detailViewModel.content)
    }
}