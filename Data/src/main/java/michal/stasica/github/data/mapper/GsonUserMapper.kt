package michal.stasica.github.data.mapper

import michal.stasica.github.data.serializable.GsonUser
import michal.stasica.github.data.serializable.GsonUserDetails
import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.model.UserDetails

class GsonUserMapper {

    fun map(gsonUser: GsonUser): User =
        with(gsonUser) {
            User(
                login = login,
                id = id,
                avatarUrl = avatarUrl
            )
        }

    fun mapDetails(gsonUserDetails: GsonUserDetails): UserDetails =
        with(gsonUserDetails) {
            UserDetails(
                login = login,
                id = id,
                avatarUrl = avatarUrl,
                bio = bio,
                name = name,
                email = email,
                hireable = hireable,
                createdAt = createdAt
            )
        }
}