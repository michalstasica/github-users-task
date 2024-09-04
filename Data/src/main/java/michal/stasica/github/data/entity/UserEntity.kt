package michal.stasica.github.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false) val login: String,
    val id: Long,
    val bio: String? = null,
    val name: String? = null,
    val email: String? = null,
    val hireable: Boolean? = null,
    val createdAt: String? = null
)
