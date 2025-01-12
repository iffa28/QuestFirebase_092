package com.example.project_firebase

import android.app.Application
import com.example.project_firebase.DI.AppContainer
import com.example.project_firebase.DI.MahasiswaContainer

class MahasiswaApplication: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}