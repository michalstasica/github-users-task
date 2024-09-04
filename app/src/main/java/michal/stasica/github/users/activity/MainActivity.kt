package michal.stasica.github.users.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import michal.stasica.github.users.compose.Screen
import michal.stasica.github.users.compose.UserDetailsScreenRoot
import michal.stasica.github.users.compose.UsersScreenRoot

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.UsersScreen.route,
                    builder = {
                        composable(route = Screen.UsersScreen.route) {
                            UsersScreenRoot(navController)
                        }
                        composable(route = "${Screen.UserDetailsScreen.route}/{login}") {
                            UserDetailsScreenRoot()
                        }
                    }
                )
            }
        }
    }
}