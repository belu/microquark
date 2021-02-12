package com.melonbase.microquark.repo;

import one.microstream.storage.types.StorageManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@ApplicationScoped
public class HelloRepo {

  @Inject
  StorageManager storage;

  @Inject
  DataRoot root;

  private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private final Lock r = readWriteLock.readLock();
  private final Lock w = readWriteLock.writeLock();

  public String getHello() {
    r.lock();
    try {
      return root.getMessage();
    } finally {
      r.unlock();
    }
  }

  public void setHello(final String msg) {
    w.lock();
    try {
      root.setMessage(msg);
      storage.storeRoot();
    } finally {
      w.unlock();
    }
  }
}
