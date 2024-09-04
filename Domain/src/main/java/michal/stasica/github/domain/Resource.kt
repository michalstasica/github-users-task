package michal.stasica.github.domain

sealed interface Resource<T> {
    class Loading<T> : Resource<T>
    class Error<T>(val throwable: Throwable?) : Resource<T>
    class Success<T>(val data: T): Resource<T>
}