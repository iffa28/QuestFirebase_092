package com.example.project_firebase.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_firebase.model.Mahasiswa
import com.example.project_firebase.repository.MahasiswaRepository
import com.example.project_firebase.ui.navigation.DestinasiDetail
import kotlinx.coroutines.launch

sealed class DetailMhsUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailMhsUiState()
    object Error : DetailMhsUiState()
    object Loading : DetailMhsUiState()
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
) : ViewModel() {

    private val nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])
    var detailMhsUiState: DetailMhsUiState by mutableStateOf(DetailMhsUiState.Loading)
        private set

    init {
        getMhsbyId()
    }

    fun getMhsbyId() {
        viewModelScope.launch {
            detailMhsUiState = DetailMhsUiState.Loading
            try {
                mhs.getMahasiswaById(nim).collect { mahasiswa ->
                    detailMhsUiState = DetailMhsUiState.Success(mahasiswa)
                }
            } catch (e: Exception) {
                detailMhsUiState = DetailMhsUiState.Error
            }
        }
    }

    fun deleteMhs(mahasiswa: Mahasiswa) {
        viewModelScope.launch {
            try {
                mhs.deleteMahasiswa(mahasiswa)
            } catch (e: Exception) {
                detailMhsUiState = DetailMhsUiState.Error
            }
        }
    }
}

