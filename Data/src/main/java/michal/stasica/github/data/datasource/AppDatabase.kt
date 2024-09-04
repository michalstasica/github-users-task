package michal.stasica.github.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import michal.stasica.github.data.dao.UserDao
import michal.stasica.github.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}