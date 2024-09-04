package michal.stasica.github.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import michal.stasica.github.domain.Resource
import michal.stasica.github.domain.model.UserDetails
import michal.stasica.github.domain.repository.UsersRepository

class GetUserDetails(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(login: String): Flow<Resource<UserDetails?>> =
        usersRepository.getUserDetails(login)
            .flowOn(Dispatchers.IO)
}