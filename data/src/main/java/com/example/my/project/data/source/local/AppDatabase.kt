package com.example.my.project.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.my.project.data.source.local.dao.PhotoDao
import com.example.my.project.data.source.local.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val photoDao: PhotoDao

    companion object {
        val DB_NAME = "GalleryDatabase.db"
    }
}