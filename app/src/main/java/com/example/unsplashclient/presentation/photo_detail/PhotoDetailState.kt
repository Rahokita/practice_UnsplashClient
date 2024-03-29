package com.example.unsplashclient.presentation.photo_detail

import com.example.unsplashclient.domain.model.PhotoDetail

data class PhotoDetailState(
    val isloading: Boolean = false,
    val photoDetail: PhotoDetail? = null,
    val error: String? = null,
)
