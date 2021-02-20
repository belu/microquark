package com.melonbase.microquark.service

sealed class ServiceResult<out E>
object NotFoundResult : ServiceResult<Nothing>()
data class RejectedResult(val reason: String) : ServiceResult<Nothing>()
object SuccessResult : ServiceResult<Nothing>()
data class SuccessWithDataResult<out E>(val entity: E) : ServiceResult<E>()