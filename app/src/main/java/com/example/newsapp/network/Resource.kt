package com.example.newsapp.network

/**
 * Please refer the class in developer android
 * @see <a href="https://developer.android.com/jetpack/guide?hl=de#addendum">Resorce<T></a>
 *
 * this class responsible for handling UI elements with api or database opration
 */

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}