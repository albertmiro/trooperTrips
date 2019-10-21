package com.albertmiro.domain.repository

sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val value: Exception) : Result<T>()
}