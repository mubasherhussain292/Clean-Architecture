package com.example.my.project.data.mapper

import com.example.my.project.data.source.local.entity.PhotoEntity
import com.example.my.project.domain.model.Photo


fun Photo.toEntity() = PhotoEntity(
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)
