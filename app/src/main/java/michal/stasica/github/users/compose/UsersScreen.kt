package michal.stasica.github.users.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import michal.stasica.github.domain.model.User
import michal.stasica.github.users.R
import michal.stasica.github.users.action.UsersAction
import michal.stasica.github.users.state.UsersState
import michal.stasica.github.users.viewmodel.UsersViewModel
import org.koin.androidx.compose.koinViewModel

typealias UserLogin = String

@Composable
fun UsersScreenRoot(
    navController: NavHostController,
    viewModel: UsersViewModel = koinViewModel()
) {
    UsersScreen(
        state = viewModel.state,
        onAction = { action ->
            if (action is UsersAction.NavigateToUser) navController.navigate("${Screen.UserDetailsScreen.route}/${action.login}")
            else Unit
            viewModel.onAction(action)
        }
    )
}

@Composable
fun UsersScreen(
    state: UsersState,
    onAction: (UsersAction) -> Unit
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            itemsIndexed(state.users) { index, user ->
                if (index == state.users.size - 1) onAction(UsersAction.LoadMoreElements(user.id))
                UserRow(
                    user = user,
                    onAction = { onAction(it) }
                )
            }
            item {
                if (state.isLoading) CircularProgressIndicator(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun UserRow(user: User, onAction: (UsersAction) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
            .height(60.dp)
            .clickable { onAction(UsersAction.NavigateToUser(user.login)) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        user.avatarUrl?.let {
            AsyncImage(model = it, contentDescription = null, modifier = Modifier.size(48.dp))
        } ?: run {
            Image(painter = painterResource(R.drawable.github_icon), contentDescription = null)
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.25F))
        Text(user.login)
    }
}