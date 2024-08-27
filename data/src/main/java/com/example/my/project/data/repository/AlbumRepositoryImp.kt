package com.example.my.project.data.repository

import com.example.my.project.data.source.remote.RetrofitService
import com.example.my.project.domain.model.Album
import com.example.my.project.domain.repository.AlbumRepository
import io.reactivex.Single

class AlbumRepositoryImp(private val retrofitService: RetrofitService) : AlbumRepository {

    override fun getAlbums(): Single<List<Album>> {
        return retrofitService.getAlbum()
    }
}