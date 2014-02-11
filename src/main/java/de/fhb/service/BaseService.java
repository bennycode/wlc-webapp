package de.fhb.service;

import de.fhb.repository.AbstractRepository;
import java.util.List;
import javax.annotation.PostConstruct;

public abstract class BaseService<T> {

  int offset = 0;
  int amount = 20;
  int currentPage = 0;

  protected abstract AbstractRepository getRepository();

  @PostConstruct
  public abstract void init();

  public BaseService() {
  }

  public abstract List<T> getItems();

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
