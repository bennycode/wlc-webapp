package de.fhb.service;

import de.fhb.repository.AbstractRepository;

public abstract class BaseService<T> {

  private int offset = 0;
  private int amount = 20;
  private int currentPage = 0;
  protected abstract AbstractRepository getRepository();

  public BaseService() {
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

}
