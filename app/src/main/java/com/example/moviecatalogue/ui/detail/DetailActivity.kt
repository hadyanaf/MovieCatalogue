package com.example.moviecatalogue.ui.detail

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.utils.JsonHelper

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type")
        val movieId = intent.getIntExtra("id", 0)

        val fileName: String
        val folder: String

        if (type.equals("movies", ignoreCase = true)) {
            fileName = "Movies.json"
            folder = "movies"
        } else {
            fileName = "TvShows.json"
            folder = "tv_shows"
        }

        val data = JsonHelper.getJsonDataFromAsset(this, fileName)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getContent(data, movieId)

        viewModel.content.let {
            Glide.with(this)
                .load(Uri.parse("file:///android_asset/$folder/${it.imagePath}"))
                .into(binding.imgPoster)

            binding.tvTitle.text = it.title
            binding.tvReleaseDate.text = it.releaseDate
            binding.tvDescription.text = it.description
        }
    }
}