package com.example.my.project.domain.usecase

import com.example.my.project.domain.model.Photo
import com.example.my.project.domain.repository.PhotoRepository
import com.example.my.project.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject


class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) : SingleUseCase<List<Photo>>() {

    private var albumId: Long? = null

    fun saveAlbumId(id: Long) {
        albumId = id
    }

    override fun buildUseCaseSingle(): Single<List<Photo>> {
        return repository.getPhotos(albumId)
    }
}
