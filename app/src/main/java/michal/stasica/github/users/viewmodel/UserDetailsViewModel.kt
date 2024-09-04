package michal.stasica.github.users.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import michal.stasica.github.domain.Resource
import michal.stasica.github.domain.usecase.GetUserDetails
import michal.stasica.github.users.state.UserDetailsState

class UserDetailsViewModel(
    private val getUserDetails: GetUserDetails,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(UserDetailsState())
        private set

    init {
        savedStateHandle.get<String>(USER_LOGIN_KEY)?.let { login ->
            viewModelScope.launch {
                getUserDetails(login)
                    .collect {
                        state = when (it) {
                            is Resource.Loading -> state.copy(isLoading = true)
                            is Resource.Success -> state.copy(
                                userDetails = it.data,
                                isLoading = false
                            )
                            is Resource.Error -> state.copy(
                                error = it.throwable,
                                isLoading = false
                            )
                        }
                    }
            }
        }
    }

    companion object {
        private const val USER_LOGIN_KEY = "login"
    }
}