package michal.stasica.github.data.datasource

import michal.stasica.github.data.mapper.RoomUserMapper
import michal.stasica.github.domain.datasource.LocalUsersDataSource
import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.model.UserDetails

class RoomUsersDataSource(
    private val db: AppDatabase,
    private val mapper: RoomUserMapper
) : LocalUsersDataSource {

    override suspend fun insert(user: User) {
        db.userDao().insert(mapper.map(user))
    }

    override suspend fun insertDetails(userDetails: UserDetails) {
        db.userDao().insert(mapper.map(userDetails))
    }

    override suspend fun getUsers(since: Long): List<User> =
        db.userDao().getUsers(since).map { mapper.mapToUser(it) }

    override suspend fun getUserDetails(login: String): UserDetails? =
        db.userDao().getUserByLogin(login)?.let { mapper.mapToUserDetails(it) }
}