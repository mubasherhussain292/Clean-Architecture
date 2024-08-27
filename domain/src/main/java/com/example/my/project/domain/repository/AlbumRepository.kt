package com.example.my.project.domain.repository

import com.example.my.project.domain.model.Album
import io.reactivex.Single

interface AlbumRepository {

    fun getAlbums(): Single<List<Album>>

}