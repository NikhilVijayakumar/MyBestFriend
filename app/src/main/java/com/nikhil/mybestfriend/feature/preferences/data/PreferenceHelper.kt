package com.nikhil.mybestfriend.feature.preferences.data

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class PreferenceHelper(val context: Context) {

    companion object{
        private const val IS_METRIC:String = "preference_metric"
    }
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveMetric(metric: Boolean){
        preferences.edit(commit=true){
            putBoolean(IS_METRIC,metric)
        }
    }
    fun isMetric() = preferences.getBoolean(IS_METRIC,true)
}