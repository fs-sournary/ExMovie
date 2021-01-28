package com.itlifelang.extrememovie.shared.di

import android.content.Context
import androidx.room.Room
import com.itlifelang.extrememovie.shared.data.api.ApiManager
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.db.AppDatabase
import com.itlifelang.extrememovie.shared.data.db.MovieDao
import com.itlifelang.extrememovie.shared.data.preference.AppPreference
import com.itlifelang.extrememovie.shared.data.preference.DefaultAppPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAppPreference(@ApplicationContext context: Context): AppPreference =
        DefaultAppPreference(context)

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi = ApiManager.getMovieApi()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao = database.getMovieDao()
}
