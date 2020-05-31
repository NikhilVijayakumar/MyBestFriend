package com.nikhil.mybestfriend.feature.preferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.nikhil.mybestfriend.R

class SettingsFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences,rootKey)
    }

}