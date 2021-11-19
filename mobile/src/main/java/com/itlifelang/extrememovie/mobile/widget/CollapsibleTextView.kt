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
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.google.android.material.textview.MaterialTextView
import com.itlifelang.extrememovie.R

class CollapsibleTextView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attr, defStyleAttr) {

    private var expanded = true

    private var toggleTransition: Transition = TransitionSet().apply {
        val rotateTransition = RotateTransition().apply { addTarget(R.id.expand_image) }
        addTransition(rotateTransition)
        addTransition(ChangeBounds())
    }

    private val root: View = LayoutInflater.from(context)
        .inflate(R.layout.view_collapsible_text, this, true)
    private val titleContainer: ViewGroup = root.findViewById(R.id.title_container)
    private val titleTextView: MaterialTextView = root.findViewById(R.id.title_text)
    private val descriptionTextView: MaterialTextView = root.findViewById(R.id.description_text)
    private val expandImageView: AppCompatImageView = root.findViewById(R.id.expand_image)

    init {
        context.withStyledAttributes(attr, R.styleable.CollapsibleTextView) {
            descriptionTextView.text = getString(R.styleable.CollapsibleTextView_description)
            titleTextView.text = getString(R.styleable.CollapsibleTextView_title)
        }
        titleContainer.setOnClickListener { toggleExpanded() }
    }

    private fun toggleExpanded() {
        expanded = expanded.not()
        TransitionManager.beginDelayedTransition(root.parent as ViewGroup, toggleTransition)
        descriptionTextView.isVisible = expanded
        expandImageView.rotation = if (expanded) 0f else 180f
    }

    fun setDescription(description: String) {
        descriptionTextView.text = description
    }

    override fun onSaveInstanceState(): Parcelable {
        val savedState = SavedState(super.onSaveInstanceState() ?: Bundle.EMPTY)
        savedState.expanded = expanded
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state)
            if (expanded != state.expanded) {
                toggleExpanded()
            }
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    internal class SavedState : BaseSavedState {

        var expanded = true

        constructor(source: Parcel) : super(source) {
            expanded = source.readInt() == 1
        }

        constructor(superState: Parcelable) : super(superState)

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(if (expanded) 1 else 0)
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

@BindingAdapter(value = ["contentDescription"])
fun CollapsibleTextView.loadContentDescription(description: String) {
    setDescription(description)
}
