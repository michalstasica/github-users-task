package michal.stasica.github.users.di

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import michal.stasica.github.data.datasource.AppDatabase
import michal.stasica.github.data.mapper.GsonUserMapper
import michal.stasica.github.data.datasource.HttpUsersDataSource
import michal.stasica.github.data.datasource.RoomUsersDataSource
import michal.stasica.github.data.mapper.RoomUserMapper
import michal.stasica.github.data.repository.UsersRepositoryImpl
import michal.stasica.github.domain.datasource.LocalUsersDataSource
import michal.stasica.github.domain.datasource.RemoteUsersDataSource
import michal.stasica.github.domain.repository.UsersRepository
import michal.stasica.github.domain.usecase.GetUserDetails
import michal.stasica.github.domain.usecase.GetUsers
import michal.stasica.github.users.viewmodel.UserDetailsViewModel
import michal.stasica.github.users.viewmodel.UsersViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "michal.stasica.github.db"
        ).build()
    }
    single<GsonUserMapper> { GsonUserMapper() }
    single<RoomUserMapper> { RoomUserMapper() }
    single<GetUsers> { GetUsers(get()) }
    single<GetUserDetails> { GetUserDetails(get()) }
    single<RemoteUsersDataSource> { HttpUsersDataSource(get()) }
    single<LocalUsersDataSource> { RoomUsersDataSource(get(), get()) }
    single<UsersRepository> { UsersRepositoryImpl(get(), get()) }
    viewModel { (savedStateHandle: SavedStateHandle) ->
        UserDetailsViewModel(
            get(),
            savedStateHandle
        )
    }
    viewModel { UsersViewModel(get()) }
}