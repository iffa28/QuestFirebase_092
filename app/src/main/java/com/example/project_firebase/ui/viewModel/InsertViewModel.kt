package com.example.project_firebase.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_firebase.model.Mahasiswa
import com.example.project_firebase.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertViewModel(
    private val mhs : MahasiswaRepository
) : ViewModel() {
    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set
    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent
        )
    }

    fun validateFields() : Boolean {
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            jenis_kelamin = if (event.jenis_kelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",
            judul_skripsi = if (event.judul_skripsi.isNotEmpty()) null else "Judul Skripsi tidak boleh kosong",
            dospemsatu = if (event.dospemsatu.isNotEmpty()) null else "Dosen Pembimbing 1 tidak boleh kosong",
            dospemdua = if (event.dospemdua.isNotEmpty()) null else "Dosen Pembimbing 2 tidak boleh kosong",
        )
        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun insertMhs() {
        if (validateFields()) {
            viewModelScope.launch {
                uiState = FormState.Loading
                try {
                    mhs.insertMahasiswa(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data berhasil ditambahkan")
                } catch (e: Exception) {
                    uiState = FormState.Error("Data gagal ditambahkan")
                }
            }
        } else {
            uiState = FormState.Error("Data tidak valid")
        }
    }

    fun resetForm() {
        uiEvent = InsertUiState()
        uiState = FormState.Idle
    }

    fun resetSnackBarMessage() {
        uiState = FormState.Idle
    }

}
sealed class FormState {
    object Idle : FormState()
    object Loading : FormState()
    data class Success(val message: String) : FormState()
    data class Error(val message: String) : FormState()
}


data class InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState()
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val alamat: String? = null,
    val jenis_kelamin: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
    val judul_skripsi: String? = null,
    val dospemsatu : String? = null,
    val dospemdua : String? = null
) {
    fun isValid(): Boolean {
        return nim == null && nama == null && alamat == null &&
                jenis_kelamin == null && kelas == null && angkatan == null && judul_skripsi == null && dospemsatu == null && dospemdua == null
    }
}

fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenis_kelamin = jenis_kelamin,
    kelas = kelas,
    angkatan = angkatan,
    judul_skripsi = judul_skripsi,
    dospemsatu = dospemsatu,
    dospemdua = dospemdua
)

data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenis_kelamin: String = "",
    val kelas: String = "",
    val angkatan: String = "",
    val judul_skripsi: String = "",
    val dospemsatu : String = "",
    val dospemdua : String = ""
)