package michal.stasica.github.users.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import michal.stasica.github.users.R
import michal.stasica.github.users.state.UserDetailsState
import michal.stasica.github.users.viewmodel.UserDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserDetailsScreenRoot(
    viewModel: UserDetailsViewModel = koinViewModel()
) {
    UserDetailsScreen(viewModel.state)
}

@Composable
fun UserDetailsScreen(state: UserDetailsState) {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .fillMaxHeight(0.3F),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    state.userDetails?.avatarUrl?.let {
                        AsyncImage(model = it, contentDescription = null)
                    } ?: run {
                        Image(
                            painter = painterResource(R.drawable.github_icon),
                            contentDescription = null
                        )
                    }
                    state.userDetails?.login?.let { Text(it, color = Color.Black) }
                }
                state.userDetails?.bio?.let {
                    Text(it, modifier = Modifier.fillMaxWidth(), color = Color.Black)
                }

                state.userDetails?.email?.let {
                    Text(it, modifier = Modifier.fillMaxWidth(), color = Color.Black)
                }
            }
        }
    }
}