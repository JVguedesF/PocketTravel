package br.com.renankoji.pockettravel.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.renankoji.pockettravel.model.User

@Dao
interface UserDAO {
    // devolve o id do user
    @Insert
    fun saveUser(user: User): Long

    // devolve a quantidade de registros atualizados
    @Update
    fun updateUser(user: User): Int

    //devolve a quantidade de registros excluídos
    @Delete
    fun deleteUser(user: User): Int

    //devolve o usuário que encontrou
    @Query("SELECT * FROM tbl_user WHERE id_user = :idUser")
    fun searchUserById(idUser: Long): User

    //devolte a lista de usuários
    @Query("SELECT * from tbl_user ORDER BY first_name ASC")
    fun userList(): List<User>
}