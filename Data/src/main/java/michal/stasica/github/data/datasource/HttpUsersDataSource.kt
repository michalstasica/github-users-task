package michal.stasica.github.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.gson.gson
import michal.stasica.github.data.mapper.GsonUserMapper
import michal.stasica.github.data.serializable.GsonUser
import michal.stasica.github.data.serializable.GsonUserDetails
import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.model.UserDetails
import michal.stasica.github.domain.datasource.RemoteUsersDataSource

class HttpUsersDataSource(
    private val mapper: GsonUserMapper
) : RemoteUsersDataSource {

    private val client by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                gson()
            }
        }
    }

    override suspend fun getUsers(since: Long): List<User> =
        client.get("$BASE_URL/users" ) {
            parameter("since", since.toString())
            parameter("per_page", 20)
        }.body<List<GsonUser>>()
            .map { mapper.map(it) }

    override suspend fun getUserDetails(login: String): UserDetails =
        client.get("$BASE_URL/users/$login")
            .body<GsonUserDetails>()
            .let { mapper.mapDetails(it) }

    companion object {
        private const val BASE_URL = "https://api.github.com"
    }
}