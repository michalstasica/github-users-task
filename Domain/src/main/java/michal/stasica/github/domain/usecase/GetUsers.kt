package michal.stasica.github.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import michal.stasica.github.domain.Resource
import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.repository.UsersRepository

class GetUsers(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(since: Long = 0): Flow<Resource<List<User>>> =
        usersRepository.getUsers(since)
            .flowOn(Dispatchers.IO)
}