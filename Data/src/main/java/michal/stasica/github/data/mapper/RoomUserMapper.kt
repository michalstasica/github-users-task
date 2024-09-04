package michal.stasica.github.data.mapper

import michal.stasica.github.data.entity.UserEntity
import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.model.UserDetails

class RoomUserMapper {

    fun map(user: User): UserEntity =
        UserEntity(login = user.login, id = user.id)

    fun map(userDetails: UserDetails): UserEntity =
        with(userDetails) {
            UserEntity(
                login = login,
                id = id,
                bio = bio,
                name = name,
                email = email,
                hireable = hireable,
                createdAt = createdAt
            )
        }

    fun mapToUser(userEntity: UserEntity): User =
        User(login = userEntity.login, id = userEntity.id)

    fun mapToUserDetails(userEntity: UserEntity): UserDetails =
        with(userEntity) {
            UserDetails(
                login = login,
                id = id,
                avatarUrl = null,
                bio = bio,
                name = name,
                email = email,
                hireable = hireable,
                createdAt = createdAt
            )
        }
}