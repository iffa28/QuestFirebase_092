package com.example.project_firebase.ui.viewModel

import com.example.project_firebase.model.Mahasiswa

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val alamat: String? = null,
    val jenis_kelamin: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
) {
    fun isValid(): Boolean {
        return nim == null && nama == null && alamat == null &&
                jenis_kelamin == null && kelas == null && angkatan == null
    }
}

fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenis_kelamin = jenis_kelamin,
    kelas = kelas,
    angkatan = angkatan
)

data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenis_kelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)