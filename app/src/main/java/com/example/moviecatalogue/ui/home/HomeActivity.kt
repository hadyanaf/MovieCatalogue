package com.example.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.databinding.ActivityMainBinding
import com.example.moviecatalogue.ui.movie.MovieFragment
import com.example.moviecatalogue.ui.tvshow.TvShowFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = HomePagerAdapter(this)
        binding.pager.adapter = sectionsPagerAdapter

        sectionsPagerAdapter.addFragment(MovieFragment())
        sectionsPagerAdapter.addFragment(TvShowFragment())

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Movies"
                }
                1 -> {
                    tab.text = "TV Shows"
                }
            }
        }.attach()
    }
}