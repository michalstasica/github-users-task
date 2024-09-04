package michal.stasica.github.users.compose

sealed class Screen(val route: String) {
    data object UsersScreen : Screen("users")
    data object UserDetailsScreen : Screen("user_details_screen")
}