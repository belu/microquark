package com.melonbase.microquark.service

import com.melonbase.microquark.repo.HelloRepo
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class HelloService @Inject constructor(private val helloRepo: HelloRepo) {

  fun getHello(): String? {
    return helloRepo.getHello()
  }

  fun setHello(msg: String) {
    helloRepo.setHello(msg)
  }
}