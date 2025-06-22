package com.example.lojasrede.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lojasrede.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    fun insertUser(usuario: Usuario): Long
    @Update
    fun updateUser(usuario: Usuario): Int
    @Delete
    fun deleteUser(usuario: Usuario): Int
    @Query("SELECT * FROM TB_Usuarios WHERE id = :id")
    fun get(id: Int): Usuario
    @Query("SELECT * FROM TB_Usuarios")
    fun getAll(): List<Usuario>
    @Query("SELECT * FROM tb_usuarios WHERE email = :email AND senha = :senha LIMIT 1")
    fun getUserByEmailAndSenha(email: String, senha: String): Usuario?
}