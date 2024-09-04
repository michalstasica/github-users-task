package michal.stasica.github.users.state

import michal.stasica.github.domain.model.UserDetails

data class UserDetailsState(
    val userDetails: UserDetails? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)