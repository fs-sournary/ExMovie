/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(
    val adult: Boolean? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val knownForDepartment: String? = null,
    val name: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val profilePath: String? = null,
    val castId: Int? = null,
    val character: String? = null,
    val creditId: String? = null,
    val order: Int? = null
) : Parcelable
