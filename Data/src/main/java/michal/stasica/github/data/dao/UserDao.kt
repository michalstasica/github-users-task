package michal.stasica.github.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import michal.stasica.github.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM users WHERE id > :since ORDER BY id LIMIT 20")
    suspend fun getUsers(since: Long): List<UserEntity>

    @Query("SELECT * FROM users WHERE login==:login")
    suspend fun getUserByLogin(login: String): UserEntity
}