package michal.stasica.github.data.serializable

import com.google.gson.annotations.SerializedName

data class GsonUser(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
