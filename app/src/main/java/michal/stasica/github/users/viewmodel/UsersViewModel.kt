package michal.stasica.github.users.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import michal.stasica.github.domain.Resource
import michal.stasica.github.domain.model.User
import michal.stasica.github.domain.usecase.GetUsers
import michal.stasica.github.users.action.UsersAction
import michal.stasica.github.users.state.UsersState

class UsersViewModel(
    private val getUsers: GetUsers
) : ViewModel() {

    var state by mutableStateOf(UsersState())
        private set

    init {
        loadUsers()
    }

    fun onAction(action: UsersAction) {
        when (action) {
            is UsersAction.LoadMoreElements -> loadUsers(action.since)
            else -> {}
        }
    }

    private fun loadUsers(since: Long = 0) {
        viewModelScope.launch {
            getUsers(since)
                .collect {
                    state = when (it) {
                        is Resource.Loading -> state.copy(isLoading = true)
                        is Resource.Success -> {
                            val updatedList = updateUsersList(state.users, it.data)
                            state.copy(
                                users = updatedList,
                                isLoading = false
                            )
                        }

                        is Resource.Error -> state.copy(
                            error = it.throwable,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun updateUsersList(existingUsers: List<User>, newUsers: List<User>): List<User> {
        val updatedMap = existingUsers.associateBy { it.id }.toMutableMap()
        for (user in newUsers) {
            updatedMap[user.id] = user
        }
        return updatedMap.values.toList()
    }

}