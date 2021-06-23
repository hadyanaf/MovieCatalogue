package com.example.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var releaseDate: String? = null,
    var imagePath: String? = null
) : Parcelable
