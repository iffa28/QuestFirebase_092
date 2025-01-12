package com.example.project_firebase.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.project_firebase.MahasiswaApplication

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(mhsApp().container.mahasiswaRepository)
        }
    }
}

fun CreationExtras.mhsApp(): MahasiswaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as MahasiswaApplication)