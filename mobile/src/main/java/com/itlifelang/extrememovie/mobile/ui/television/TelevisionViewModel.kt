/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.television

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itlifelang.extrememovie.mobile.data.Genre
import com.itlifelang.extrememovie.mobile.data.Television
import com.itlifelang.extrememovie.mobile.mapper.mapToMobile
import com.itlifelang.extrememovie.model.TelevisionModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.result.data
import com.itlifelang.extrememovie.shared.usecase.television.GetFirstPageAiringTodayTelevisionUseCase
import com.itlifelang.extrememovie.shared.usecase.television.GetFirstPageGenreTelevisionsUseCase
import com.itlifelang.extrememovie.shared.usecase.television.GetFirstPageOnTheAirTelevisionUseCase
import com.itlifelang.extrememovie.shared.usecase.television.GetFirstPagePopularTelevisionUseCase
import com.itlifelang.extrememovie.shared.usecase.television.GetFirstPageTopRatedTelevisionUseCase
import com.itlifelang.extrememovie.shared.usecase.television.GetTelevisionGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class TelevisionViewModel @Inject constructor(
    getFirstPageAiringTodayTelevisionUseCase: GetFirstPageAiringTodayTelevisionUseCase,
    getFirstPageOnTheAirTelevisionUseCase: GetFirstPageOnTheAirTelevisionUseCase,
    getFirstPagePopularTelevisionUseCase: GetFirstPagePopularTelevisionUseCase,
    getFirstPageTopRatedTelevisionUseCase: GetFirstPageTopRatedTelevisionUseCase,
    getTelevisionGenresUseCase: GetTelevisionGenresUseCase,
    getFirstPageGenreTelevisionsUseCase: GetFirstPageGenreTelevisionsUseCase
) : ViewModel() {

    private val _hasConnection = MutableStateFlow(false)
    val hasConnection: StateFlow<Boolean> = _hasConnection

    val airingTodayTelevisions: StateFlow<List<Television>?> =
        _hasConnection.flatMapLatest { hasConnection ->
            if (hasConnection) {
                getFirstPageAiringTodayTelevisionUseCase(Unit)
                    .map {
                        it.data?.map { televisionModel -> televisionModel.mapToMobile() }
                    }
            } else {
                flowOf(null)
            }
        }.stateIn(viewModelScope, Lazily, null)

    val onTheAirTelevisions: StateFlow<List<Television>?> =
        _hasConnection.flatMapLatest { hasConnection ->
            if (hasConnection) {
                getFirstPageOnTheAirTelevisionUseCase(Unit)
                    .map {
                        it.data?.map { televisionModel -> televisionModel.mapToMobile() }
                    }
            } else {
                flowOf(null)
            }
        }.stateIn(viewModelScope, Lazily, null)

    val popularTelevisions: StateFlow<List<Television>?> =
        _hasConnection.flatMapLatest { hasConnection ->
            if (hasConnection) {
                getFirstPagePopularTelevisionUseCase(Unit)
                    .map {
                        it.data?.map { televisionModel -> televisionModel.mapToMobile() }
                    }
            } else {
                flowOf(null)
            }
        }.stateIn(viewModelScope, Lazily, null)

    val topRatedTelevisions: StateFlow<List<Television>?> =
        _hasConnection.flatMapLatest { hasConnection ->
            if (hasConnection) {
                getFirstPageTopRatedTelevisionUseCase(Unit)
                    .map {
                        it.data?.map { televisionModel -> televisionModel.mapToMobile() }
                    }
            } else {
                flowOf(null)
            }
        }.stateIn(viewModelScope, Lazily, null)

    val genres: StateFlow<List<Genre>?> = _hasConnection.flatMapLatest { hasConnection ->
        if (hasConnection) {
            getTelevisionGenresUseCase(Unit)
                .map {
                    val genres = it.data?.map { genreModel -> genreModel.mapToMobile() }
                    setGenre(genres?.getOrNull(0))
                    genres
                }
        } else {
            flowOf(null)
        }
    }.stateIn(viewModelScope, Lazily, null)

    private val _genre = MutableStateFlow<Genre?>(null)
    val genre: StateFlow<Genre?> = _genre
    private val genreTelevisionResult: StateFlow<Result<List<TelevisionModel>>> =
        _genre.flatMapLatest {
            val id =
                it?.id ?: return@flatMapLatest flowOf(Result.Error(Exception("Genre id is empty")))
            getFirstPageGenreTelevisionsUseCase(id)
        }.stateIn(viewModelScope, Lazily, Result.Loading)
    val genreTelevisions: StateFlow<List<Television>?> = genreTelevisionResult.mapLatest {
        it.data?.map { televisionModel -> televisionModel.mapToMobile() }
    }.stateIn(viewModelScope, Lazily, null)
    val genreTelevisionLoading: StateFlow<Boolean> = genreTelevisionResult.mapLatest {
        it is Result.Loading
    }.stateIn(viewModelScope, Lazily, false)

    fun setConnection(value: Boolean) {
        if (_hasConnection.value != value) {
            // Use [postValue] because checking connectivity in the background
            _hasConnection.value = value
        }
    }

    fun setGenre(newGenre: Genre?) {
        if (_genre.value != newGenre) {
            newGenre?.let { _genre.value = it }
        }
    }
}
