package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.Content
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MovieViewModel : ViewModel() {
    var content = mutableListOf<Content>()

    fun getContent(data: String?) {
        if (!data.isNullOrEmpty()) {
            val gson = Gson()
            val listContents = object : TypeToken<List<Content>>() {}.type

            content = gson.fromJson(data, listContents)
        }
    }
}