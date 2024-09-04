package michal.stasica.github.domain.datasource

import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.model.UserDetails

interface LocalUsersDataSource {
    suspend fun insert(user: User)
    suspend fun insertDetails(userDetails: UserDetails)
    suspend fun getUsers(since: Long): List<User>
    suspend fun getUserDetails(login: String): UserDetails?
}