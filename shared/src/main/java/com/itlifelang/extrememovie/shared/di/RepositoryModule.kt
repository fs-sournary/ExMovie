/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.di

import com.itlifelang.extrememovie.shared.repository.movie.DefaultMovieRepository
import com.itlifelang.extrememovie.shared.repository.movie.MovieRepository
import com.itlifelang.extrememovie.shared.repository.profile.DefaultProfileRepository
import com.itlifelang.extrememovie.shared.repository.profile.ProfileRepository
import com.itlifelang.extrememovie.shared.repository.signin.DefaultSignInRepository
import com.itlifelang.extrememovie.shared.repository.signin.SignInRepository
import com.itlifelang.extrememovie.shared.repository.tv.DefaultTvRepository
import com.itlifelang.extrememovie.shared.repository.tv.TvRepository
import com.itlifelang.extrememovie.shared.repository.walkthrough.DefaultWalkThroughRepository
import com.itlifelang.extrememovie.shared.repository.walkthrough.WalkThroughRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(): MovieRepository = DefaultMovieRepository()

    @Singleton
    @Provides
    fun provideProfileRepository(): ProfileRepository = DefaultProfileRepository()

    @Singleton
    @Provides
    fun provideSignInRepository(): SignInRepository = DefaultSignInRepository()

    @Singleton
    @Provides
    fun provideTvRepository(): TvRepository = DefaultTvRepository()

    @Singleton
    @Provides
    fun provideWalkThroughRepository(): WalkThroughRepository = DefaultWalkThroughRepository()
}
