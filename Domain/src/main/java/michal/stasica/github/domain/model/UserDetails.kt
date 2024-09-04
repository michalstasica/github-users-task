package michal.stasica.github.domain.model

data class UserDetails(
    val login: String,
    val id: Long,
    val avatarUrl: String? = null,
    val bio: String?,
    val name: String?,
    val email: String?,
    val hireable: Boolean?,
    val createdAt: String?
)
