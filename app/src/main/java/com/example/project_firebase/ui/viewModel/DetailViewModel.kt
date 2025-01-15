package com.example.project_firebase.ui.viewModel

import com.example.project_firebase.model.Mahasiswa

sealed class DetailMhsUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailMhsUiState()
    object Error : DetailMhsUiState()
    object Loading : DetailMhsUiState()
}

