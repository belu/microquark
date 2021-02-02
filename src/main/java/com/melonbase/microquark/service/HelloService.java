package com.melonbase.microquark.service;

import com.melonbase.microquark.repo.HelloRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HelloService {

  @Inject
  HelloRepo helloRepo;

  public String getHello() {
    return helloRepo.getHello();
  }

  public void setHello(final String msg) {
    helloRepo.setHello(msg);
  }
}
