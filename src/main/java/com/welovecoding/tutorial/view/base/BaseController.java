package com.welovecoding.controller;

import com.welovecoding.config.Packages;
import com.welovecoding.entities.BaseEntity;
import com.welovecoding.exception.ConstraintViolationBagException;
import com.welovecoding.service.BaseService;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;

@Dependent
/**
 * TODO:
 * http://stackoverflow.com/questions/197986/what-causes-javac-to-issue-the-uses-unchecked-or-unsafe-operations-warning
 */
public abstract class BaseController<T extends BaseEntity, E extends BaseService> implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final Logger LOG = Logger.getLogger(BaseController.class.getName());

  // Pagination
  protected int offset;
  protected int amount;
  protected int currentPage;
  protected int totalPages;

  // Data
  protected T item;
  private List<T> items;

  public abstract E getService();

  @SuppressWarnings("unchecked")
  public String remove() {
    String template = "Deleting item: {0}";
    LOG.log(Level.INFO, template, item.getName());
    getService().remove(item);
    return "";
  }

  @SuppressWarnings("unchecked")
  public String edit() {
    String template = "Saving item: {0}";
    LOG.log(Level.INFO, template, item.getName());
    System.out.println("EDIT ITEM: " + item.toString());

    try {

      getService().edit(item);

    } catch (ConstraintViolationBagException ex) {

      FacesContext context = FacesContext.getCurrentInstance();
      ResourceBundle backendText = context.getApplication().getResourceBundle(context, Packages.BACKEND_MESSAGES_NAME);

      Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
      for (ConstraintViolation<?> constraintViolation : constraintViolations) {

        String key;

        try {
          key = backendText.getString("admin.form.label." + constraintViolation.getPropertyPath());
        } catch (java.util.MissingResourceException resourceEx) {
          // TODO: Should be combined with text handling in ComponentFactory
          key = constraintViolation.getPropertyPath().toString();
        }

        String summary = key + ": " + constraintViolation.getMessage();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        context.addMessage(GenFormBaseController.ERROR_MESSAGES_NAME, message);
      }

    }

    return "";
  }

  /**
   * TODO: Getter should be plain! Data should retrived from JPA somewhere else!
   *
   * http://stackoverflow.com/questions/197986/what-causes-javac-to-issue-the-uses-unchecked-or-unsafe-operations-warning
   * http://stackoverflow.com/questions/8971954/how-to-avoid-having-to-use-suppresswarningsunchecked
   *
   * @return Generic list
   */
  @SuppressWarnings("unchecked")
  public List<T> getItems() {
    this.items = getService().findRange(offset, amount);
    return this.items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  public int getItemSize() {
    return getService().count();
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

  public int getTotalPages() {
    if (amount == 0) {
      return 1;
    }

    int pages = getItemSize() / amount;
    int mod = getItemSize() % amount;

    if (mod > 0) {
      pages += 1;
    }

    if (pages == 0) {
      pages = 1;
    }

    return pages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }
}
