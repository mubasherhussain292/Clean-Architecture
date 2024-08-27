package com.example.my.project.data.repository

import com.example.my.project.data.mapper.toEntity
import com.example.my.project.data.source.local.AppDatabase
import com.example.my.project.data.source.remote.RetrofitService
import com.example.my.project.domain.model.Photo
import com.example.my.project.domain.repository.PhotoRepository
import io.reactivex.Single

class PhotoRepositoryImp(
    private val database: AppDatabase,
    private val retrofitService: RetrofitService
) : PhotoRepository {

    override fun isFavorite(photoId: Long): Boolean {
        return database.photoDao.loadOneByPhotoId(photoId) != null
    }

    override fun deletePhoto(photo: Photo) {
        database.photoDao.delete(photo.toEntity())
    }

    override fun addPhoto(photo: Photo) {
        database.photoDao.insert(photo.toEntity())
    }

    override fun getPhotoDetail(photoId: Long?): Single<Photo> {
        return retrofitService.getPhotoDetail(photoId!!)
    }

    override fun getPhotos(albumId: Long?): Single<List<Photo>> {
        return retrofitService.getPhotos(albumId!!)
    }
}