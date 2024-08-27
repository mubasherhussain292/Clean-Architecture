package com.example.my.project.domain.usecase

import com.example.my.project.domain.model.Album
import com.example.my.project.domain.repository.AlbumRepository
import com.example.my.project.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import jakarta.inject.Inject


class GetAlbumUseCase @Inject constructor(
    private val repository: AlbumRepository
) : SingleUseCase<List<Album>>() {

    override fun buildUseCaseSingle(): Single<List<Album>> {
        return repository.getAlbums()
    }
}
