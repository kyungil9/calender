package com.calender.domain.model

import java.io.IOException

sealed class Result<out T> {
    object Uninitialized : Result<Nothing>()
    object Loading : Result<Nothing>()
    object Empty : Result<Nothing>()
    data class Success<T>(val data : T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>(){
        val isNetWorkError = exception is IOException
    }
}

fun <T> Result<T>.successOrNull() : T? = if (this is Result.Success<T>){
    data
}else{
    null
}

fun <T> Result<T>.throwableOrNull(): Throwable? = if (this is Result.Error){
    exception
}else{
    null
}