package com.melonbase.microquark.service

sealed class Result<out E>
object NotFound : Result<Nothing>()
data class Failure(val reason: String) : Result<Nothing>()
object VoidSuccess : Result<Nothing>()
data class Success<out E>(val entity: E) : Result<E>()