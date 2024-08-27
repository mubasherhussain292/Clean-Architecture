package com.example.my.project.domain.usecase

import com.example.my.project.domain.model.Photo
import com.example.my.project.domain.repository.PhotoRepository
import com.example.my.project.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject


class GetPhotoDetailUseCase @Inject constructor(
    private val repository: PhotoRepository
) : SingleUseCase<Photo>() {

    private var photoId: Long? = null

    fun savePhotoId(id: Long) {
        photoId = id
    }

    override fun buildUseCaseSingle(): Single<Photo> {
        return repository.getPhotoDetail(photoId)
    }

    fun deleteAsFavorite(photo: Photo) {
        repository.deletePhoto(photo)
    }

    fun addAsFavorite(photo: Photo) {
        repository.addPhoto(photo)
    }

    fun isFavorite(photoId: Long): Boolean {
        return repository.isFavorite(photoId)
    }
}
