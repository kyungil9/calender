package com.calender.presentation.view.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.calender.presentation.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}