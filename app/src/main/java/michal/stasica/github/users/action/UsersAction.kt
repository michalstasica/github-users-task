package michal.stasica.github.users.action

sealed class UsersAction {
    data class LoadMoreElements(val since: Long) : UsersAction()
    data class NavigateToUser(val login: String): UsersAction()
}