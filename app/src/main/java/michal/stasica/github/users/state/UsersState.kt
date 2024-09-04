package michal.stasica.github.users.state

import michal.stasica.github.domain.model.User

data class UsersState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)