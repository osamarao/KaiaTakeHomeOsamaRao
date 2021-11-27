package com.example.kaiacasestudy

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp(Application::class)
class KaiaApplication :  Hilt_KaiaApplication()


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favorites")
