package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DummyData
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
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
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testViewPager() {
        Thread.sleep(3000)
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
        onView(allOf(withId(R.id.tv_title), withText("A Star Is Born")))
        onView(withId(R.id.tv_description)).check(ViewAssertions.matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.tv_title),
                withText("Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.")
            )
        )
        onView(withId(R.id.tv_release_date)).check(ViewAssertions.matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_release_date), withText("05/10/2018")))
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
        onView(allOf(withId(R.id.tv_title), withText("Arrow")))
        onView(withId(R.id.tv_description)).check(ViewAssertions.matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.tv_title),
                withText("Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.")
            )
        )
        onView(withId(R.id.tv_release_date)).check(ViewAssertions.matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_release_date), withText("10/10/2012")))
        onView(withId(R.id.img_poster)).check(ViewAssertions.matches(isDisplayed()))
    }
}