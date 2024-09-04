package michal.stasica.github.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import michal.stasica.github.domain.Resource
import michal.stasica.github.domain.datasource.LocalUsersDataSource
import michal.stasica.github.domain.datasource.RemoteUsersDataSource
import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.model.UserDetails
import michal.stasica.github.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val localUsersDataSource: LocalUsersDataSource,
    private val remoteUsersDataSource: RemoteUsersDataSource
) : UsersRepository {

    override suspend fun insert(user: User) {
        localUsersDataSource.insert(user)
    }

    override suspend fun getUsers(since: Long): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        val cachedUsers = localUsersDataSource.getUsers(since)
        emit(Resource.Success(cachedUsers))
        try {
            val updatedUsers = remoteUsersDataSource.getUsers(since)
            emit(Resource.Success(updatedUsers))
            updatedUsers.forEach { localUsersDataSource.insert(it) }
        } catch (e: Exception) {
            emit(Resource.Error(e.cause))
        }
    }

    override suspend fun getUserDetails(login: String): Flow<Resource<UserDetails?>> = flow {
        emit(Resource.Loading())
        val cachedUserDetails = localUsersDataSource.getUserDetails(login)
        emit(Resource.Success(cachedUserDetails))
        try {
            val updatedUserDetails = remoteUsersDataSource.getUserDetails(login)
            emit(Resource.Success(updatedUserDetails))
            localUsersDataSource.insertDetails(updatedUserDetails)
        } catch (e: Exception) {
            emit(Resource.Error(e.cause))
        }
    }
}