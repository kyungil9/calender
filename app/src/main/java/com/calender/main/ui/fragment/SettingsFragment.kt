package com.calender.main.ui.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.calender.main.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}