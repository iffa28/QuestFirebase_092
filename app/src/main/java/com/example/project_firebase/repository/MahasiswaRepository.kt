package com.example.project_firebase.repository

import com.example.project_firebase.model.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface MahasiswaRepository {
    suspend fun getMahasiswa(): Flow<List<Mahasiswa>>
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa(nim: String)
}