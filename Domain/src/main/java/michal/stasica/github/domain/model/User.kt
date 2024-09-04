package michal.stasica.github.domain.model

data class User(
    val login: String,
    val id: Long,
    val avatarUrl: String? = null
)
