package com.example.unsplashclient.presentation.photo_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.unsplashclient.domain.model.PhotoDetail

@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()){
        when{
            state.isloading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            !state.error.isNullOrBlank() -> {
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colors.error,
                    )
            }
            else -> {
                state.photoDetail?.let{ photoDetail ->
                    AsyncImage(
                        model = photoDetail.imageUrl,
                        contentDescription = photoDetail.description,
                        modifier = Modifier.fillMaxWidth(),
                        )
                }

            }
        }
    }
}

@Composable
fun PhotoDetailContent(photoDetail:PhotoDetail){
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Box(modifier = Modifier.heightIn(min = 200.dp)){
            var isLoadingImage by remember { mutableStateOf(true) }
            if (isLoadingImage){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            AsyncImage(
                model = photoDetail.imageUrl,
                contentDescription = photoDetail.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 0, topEndPercent = 0,
                            bottomStartPercent = 5, bottomEndPercent = 5,
                        )
                    ),
                onSuccess = { isLoadingImage = false},
            )
        }
    }
}