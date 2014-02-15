package de.fhb.controller;

import de.fhb.entities.BaseEntity;
import de.fhb.service.BaseService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;

@Dependent
public abstract class BaseController<T extends BaseEntity, E extends BaseService> implements Serializable {

  private static final Logger LOG = Logger.getLogger(BaseController.class.getName());

  private int offset = 0;
  private int amount = 20;
  private int currentPage = 1;

  protected T item;
  private List<T> items;

  public abstract E getService();

  public String remove() {
    String template = "Deleting item: {0}";
    LOG.log(Level.INFO, template, item.getName());
    getService().remove(item);
    return "";
  }

  public String edit() {
    String template = "Saving item: {0}";
    LOG.log(Level.INFO, template, item.getName());
    getService().edit(item);
    return "";
  }

  public List<T> getItems() {
    return getService().findAll();
  }

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
