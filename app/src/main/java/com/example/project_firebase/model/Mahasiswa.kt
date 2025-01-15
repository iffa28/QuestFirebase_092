package com.example.project_firebase.model

data class Mahasiswa (
    val nim: String,
    val nama: String,
    val jenis_kelamin: String,
    val alamat: String,
    val kelas: String,
    val angkatan: String,
    val judul_skripsi: String,
    val dospemsatu: String,
    val dospemdua: String
){
    constructor(): this("","","","","","", "", "", "")
}