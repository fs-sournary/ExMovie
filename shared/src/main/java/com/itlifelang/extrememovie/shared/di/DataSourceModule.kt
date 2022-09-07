/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.di

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.itlifelang.extrememovie.shared.data.api.ApiManager
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.TelevisionApi
import com.itlifelang.extrememovie.shared.data.db.AppDatabase
import com.itlifelang.extrememovie.shared.data.db.LibraryMovieDao
import com.itlifelang.extrememovie.shared.data.db.SearchMovieDao
import com.itlifelang.extrememovie.shared.data.preference.AppPreference
import com.itlifelang.extrememovie.shared.data.preference.DataStorePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    private val Context.dataStore by preferencesDataStore(
        name = DataStorePreference.PREF_NAME,
        produceMigrations = {
            listOf(SharedPreferencesMigration(it, DataStorePreference.PREF_NAME))
        }
    )

    @Singleton
    @Provides
    fun provideAppPreference(@ApplicationContext context: Context): AppPreference {
        return DataStorePreference(context.dataStore)
    }

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi = ApiManager.getRetrofit().create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideTelevisionApi(): TelevisionApi {
        return ApiManager.getRetrofit().create(TelevisionApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideSearchMovieDao(database: AppDatabase): SearchMovieDao = database.getSearchMovieDao()

    @Singleton
    @Provides
    fun provideLibraryMovieDao(database: AppDatabase): LibraryMovieDao {
        return database.getLibraryMovieDao()
    }
}
