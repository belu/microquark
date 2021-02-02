package com.melonbase.microquark.repo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HelloRepo {

  @Inject
  MicroStreamStorage storage;

  public String getHello() {
    return storage.getRoot().getMessage();
  }

  public void setHello(final String msg) {
    storage.getRoot().setMessage(msg);
    storage.storeRoot();
  }
}
