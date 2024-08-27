package com.example.my.project.sample.album

import androidx.lifecycle.ViewModel
import com.example.my.project.domain.usecase.GetAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val getAlbumListUseCase: GetAlbumUseCase) : ViewModel() {


    fun loadAlbum() {
        getAlbumListUseCase.execute(
            onSuccess = {},
            onError = {}
        )
    }
}