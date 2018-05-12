package com.captech.android.demos.slices

import android.app.Application
import com.captech.android.demos.slices.state.AppState

class SlicesApplication : Application() {

    lateinit var appState: AppState

    override fun onCreate() {
        super.onCreate()
        appState = AppState()
    }
}