package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.Content
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DetailViewModel : ViewModel() {
    var content = Content()

    fun getContent(data: String?, id: Int) {
        if (!data.isNullOrEmpty()) {
            val gson = Gson()
            val listContents = object : TypeToken<List<Content>>() {}.type
            val res: List<Content> = gson.fromJson(data, listContents)

            content = res.find { c -> c.id == id } ?: Content()
        }
    }
}