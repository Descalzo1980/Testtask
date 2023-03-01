package ru.stas.repository

import androidx.lifecycle.LiveData
import ru.stas.data.UserDao
import ru.stas.model.User

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    fun getUserByEmailAndFirstName(email: String, firstName: String): LiveData<User> {
        return userDao.getUserByEmailAndFirstName(email, firstName)
    }
}