package com.example.lojasrede.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_Usuarios")
class Usuario {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "nome")
    var nome: String = ""

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "senha")
    var senha: String = ""
}