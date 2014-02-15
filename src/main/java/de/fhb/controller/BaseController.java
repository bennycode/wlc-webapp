package de.fhb.controller;

import de.fhb.entities.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;

@Dependent
public abstract class BaseController<T extends BaseEntity> implements Serializable {

  private int offset = 0;
  private int amount = 20;
  private int currentPage = 0;

  protected T item;
  private List<T> items;

  public abstract String remove();

  public abstract String edit();

  public abstract List<T> getItems();

  public void setItems(List<T> items) {
    this.items = items;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public T getItem() {
    return item;
  }

  public void setItem(T item) {
    this.item = item;
  }
}
