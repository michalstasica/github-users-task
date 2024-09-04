package michal.stasica.github.data.serializable

import com.google.gson.annotations.SerializedName

data class GsonUserDetails(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val bio: String,
    val name: String,
    val email: String,
    val hireable: Boolean,
    @SerializedName("created_at") val createdAt: String
)