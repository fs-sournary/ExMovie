/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.widget

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.mobile.data.Genre

class GenreChipGroup @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ChipGroup(context, attr, defStyleAttr) {

    private var checkId: Int = -1

    fun setGenres(inflater: LayoutInflater, items: List<Genre>?, click: (Genre) -> Unit) {
        if (items.isNullOrEmpty()) return
        removeAllViews()
        items.forEach {
            val chip = inflater.inflate(R.layout.layout_movie_genre, this, false) as Chip
            chip.text = it.name
            chip.id = it.id ?: -1
            chip.setOnClickListener { _ -> click(it) }
            addView(chip)
        }
        val currentCheckId = items.find { checkId == it.id }?.id
        if (currentCheckId == null) {
            items.getOrNull(0)?.id?.let { check(it) }
        } else {
            check(currentCheckId)
        }
        setOnCheckedChangeListener { _, checkedId -> checkId = checkedId }
    }

    override fun onSaveInstanceState(): Parcelable {
        val savedState = SavedState(super.onSaveInstanceState() ?: Bundle.EMPTY)
        savedState.checkId = checkId
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state)
            checkId = state.checkId
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    internal class SavedState : BaseSavedState {

        var checkId: Int = -1

        constructor(source: Parcel) : super(source) {
            checkId = source.readInt()
        }

        constructor(superState: Parcelable) : super(superState)

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(checkId)
        }

        companion object {

            @JvmField
            val CREATOR = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(source: Parcel): SavedState = SavedState(source)

                override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
            }
        }
    }
}
