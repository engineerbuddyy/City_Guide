package com.example.cityguide.Module

import android.app.Application
import androidx.room.Room
import com.example.cityguide.data.local.database.AppDatabase
import com.example.cityguide.data.local.res.PlaceDao
import com.example.cityguide.data.repository.PlaceRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "app_database").build()

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): PlaceDao = db.placeDao()

    @Provides
    @Singleton
    fun provideRepository(dao: PlaceDao): PlaceRepo = PlaceRepo(dao)

}
