package com.example.project_firebase.ui.viewModel

import com.example.project_firebase.model.Mahasiswa

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