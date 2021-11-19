/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.profile

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.itlifelang.extrememovie.R

class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_setting, rootKey)
    }

    companion object {

        const val TAG = "Setting"
    }
}
