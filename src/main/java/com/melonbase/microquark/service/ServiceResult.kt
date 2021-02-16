package com.melonbase.microquark.service

sealed class ServiceResult
object NotFoundResult : ServiceResult()
data class RejectedResult(val reason: String) : ServiceResult()
object SuccessResult : ServiceResult()
data class SuccessWithDataResult<out E>(val entity: E) : ServiceResult()