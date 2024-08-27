package com.example.my.project.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.my.project.data.source.local.entity.PhotoEntity
import com.example.my.project.domain.model.Photo

@Dao
interface PhotoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: PhotoEntity): Long

    @Query("SELECT * FROM Photo")
    fun loadAll(): MutableList<PhotoEntity>

    @Delete
    fun delete(photo: PhotoEntity)

    @Query("DELETE FROM Photo")
    fun deleteAll()

    @Query("SELECT * FROM Photo where id = :photoId")
    fun loadOneByPhotoId(photoId: Long): PhotoEntity?

    @Query("SELECT * FROM Photo where title = :photoTitle")
    fun loadOneByPhotoTitle(photoTitle: String): PhotoEntity?

    @Update
    fun update(photo: PhotoEntity)
}