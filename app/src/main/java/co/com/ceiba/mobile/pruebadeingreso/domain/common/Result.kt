package co.com.ceiba.mobile.pruebadeingreso.domain.common


sealed class Result<out T> {
    data class Success<T>(val data: T?) : Result<T>()
    object Loading : Result<Nothing>()
    data class Failure<T>(val data: T?) : Result<T>()
}