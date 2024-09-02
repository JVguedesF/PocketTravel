package br.com.renankoji.pockettravel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_user") var idUser: Long = 0,
    @ColumnInfo(name = "first_name") var firstName: String = "",
    @ColumnInfo(name = "last_name")var lastName: String = "",
    var email: String = "",
)