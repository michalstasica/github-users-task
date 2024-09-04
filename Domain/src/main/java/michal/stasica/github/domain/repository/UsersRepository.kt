package michal.stasica.github.domain.repository

import kotlinx.coroutines.flow.Flow
import michal.stasica.github.domain.Resource
import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.model.UserDetails

interface UsersRepository {
    suspend fun insert(user: User)
    suspend fun getUsers(since: Long): Flow<Resource<List<User>>>
    suspend fun getUserDetails(login: String): Flow<Resource<UserDetails?>>
}