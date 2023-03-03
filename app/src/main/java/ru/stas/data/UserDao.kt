package ru.stas.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.stas.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE email = :email AND firstName = :firstName")
    fun getUserByEmailAndFirstName(email: String, firstName: String): LiveData<User>

    @Query("SELECT * FROM user_table WHERE firstName = :firstName")
    fun authenticationName(firstName: String): LiveData<User>
}