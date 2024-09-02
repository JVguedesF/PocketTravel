package br.com.fiap.pockettravel.database.repository

import android.content.Context
import br.com.renankoji.pockettravel.database.dao.UserDB
import br.com.renankoji.pockettravel.model.User

class UserRepository(context: Context) {

    var db = UserDB.getDatabase(context).userDAO()

    fun saveUser(user: User): Long {
        return db.saveUser(user = user)
    }

    fun updateUser(user: User): Int {
        return db.updateUser(user = user)
    }

    fun deleteUser(user: User): Int {
        return db.deleteUser(user = user)
    }

    fun searchUserById(idUser: Long): User {
        return db.searchUserById(idUser = idUser)
    }

    fun userList(): List<User> {
        return db.userList()
    }

}