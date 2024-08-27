package com.example.my.project.domain.repository

import com.example.my.project.domain.model.Photo
import io.reactivex.Single

interface PhotoRepository {

    fun getPhotos(albumId: Long?): Single<List<Photo>>
    fun getPhotoDetail(photoId: Long?): Single<Photo>
    fun deletePhoto(photo: Photo)
    fun addPhoto(photo: Photo)
    fun isFavorite(photoId: Long): Boolean

}