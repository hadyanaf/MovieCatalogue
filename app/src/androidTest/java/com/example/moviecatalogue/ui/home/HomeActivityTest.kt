package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DummyData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    private val dummyMovies = DummyData.generateDummyDataMovie()
    private val dummyTVShows = DummyData.generateDummyDataTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun testViewPager() {
        onView(withId(R.id.tab_layout)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.pager))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.rv_tv_show)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testListTVShow() {
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTVShows.size
            )
        )
    }

    @Test
    fun testListMovie() {
        onView(withId(R.id.pager)).perform(ViewActions.swipeRight())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.pager)).perform(ViewActions.swipeRight())
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_title)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun loadDetailTVShow() {
        onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
        Thread.sleep(100)
        onView(withId(R.id.rv_tv_show)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_title)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(ViewAssertions.matches(isDisplayed()))
    }
}