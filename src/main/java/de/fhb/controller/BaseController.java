package de.fhb.controller;

import java.io.Serializable;

public abstract class BaseController<T> implements Serializable {

  protected T item;

  public T getItem() {
    return item;
  }

  public void setItem(T item) {
    this.item = item;
  }

  public abstract String remove();

  public abstract String edit();

}
