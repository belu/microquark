package com.melonbase.microquark.repo;

import one.microstream.storage.types.StorageManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HelloRepo {

  @Inject
  StorageManager storage;

  @Inject
  DataRoot root;

  public String getHello() {
    return root.getMessage();
  }

  public void setHello(final String msg) {
    root.setMessage(msg);
    storage.storeRoot();
  }
}
