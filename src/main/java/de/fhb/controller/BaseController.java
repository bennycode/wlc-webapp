package de.fhb.controller;

import de.fhb.entities.BaseEntity;
import de.fhb.service.BaseService;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.enterprise.context.Dependent;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

@Dependent
public abstract class BaseController<T extends BaseEntity, E extends BaseService> implements Serializable {

  private static final Logger LOG = Logger.getLogger(BaseController.class.getName());

  private int offset = 0;
  private int amount = 20;
  private int currentPage = 1;

  protected T item;
  private List<T> items;

  private HtmlForm form;

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
    System.out.println("EDIT ITEM: " + item.toString());
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

  public void setForm(HtmlForm form) {
    System.out.println("setForm");
    this.form = form;
  }

  public HtmlForm getForm() {
    System.out.println("getForm");
//    if (form == null) {
    Application app = FacesContext.getCurrentInstance().getApplication();
    form = (HtmlForm) app.createComponent(HtmlForm.COMPONENT_TYPE);

    HtmlPanelGrid panelGrid = (HtmlPanelGrid) app.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
    form.getChildren().add(panelGrid);
    panelGrid.setColumns(2);

    if (item != null) {
      for (Map.Entry<String, Class<?>> entry : getAttributes(item).entrySet()) {
        HtmlOutputText key = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
        key.setValue(capitalize(entry.getKey()));
        panelGrid.getChildren().add(key);

        HtmlInputText value = (HtmlInputText) app.createComponent(HtmlInputText.COMPONENT_TYPE);
        String valueExpression = "#{" + getELClassname() + ".item." + entry.getKey() + "}";
        System.out.println("ValueExpression: " + valueExpression);
        value.setValueExpression("value", createValueExpression(valueExpression, entry.getValue()));
        panelGrid.getChildren().add(value);
      }
    } else {
      HtmlOutputText error = new HtmlOutputText();
      error.setValue("No Item is set!");
      panelGrid.getChildren().add(error);
    }

//    }
    return form;
  }

  private Map<String, Class<?>> getAttributes(Object obj) {
    Map<String, Class<?>> attributes = new HashMap<>();

    Field[] objectFields = obj.getClass().getDeclaredFields();
    Field[] superclassFields = obj.getClass().getSuperclass().getDeclaredFields();

    // check for class
    for (Field field : objectFields) {
      System.out.println("Fieldname: " + field.getName() + " Fieldtype: " + field.getType());

      if (checkGetterPresent(obj.getClass(), field) && isJavaLang(field.getType())) {
        attributes.put(field.getName(), field.getType());
      }
    }
    // check for superclass
    for (Field field : superclassFields) {
      System.out.println("Fieldname: " + field.getName() + " Fieldtype: " + field.getType());

      if (checkGetterPresent(obj.getClass().getSuperclass(), field) && isJavaLang(field.getType())) {
        attributes.put(field.getName(), field.getType());
      }
    }

    return attributes;
  }

  private String capitalize(String line) {
    return Character.toUpperCase(line.charAt(0)) + line.substring(1);
  }

  private String getELClassname() {
    String classname = this.getClass().getSimpleName();
    classname = Character.toLowerCase(classname.charAt(0)) + classname.substring(1);
    return classname;
  }

  private boolean checkGetterPresent(Class<?> clazz, Field field) {
    Class<?>[] emptyParamObjects = new Class<?>[]{};
    boolean isPresent = false;
    try {
      clazz.getDeclaredMethod("get" + capitalize(field.getName()), emptyParamObjects);
      isPresent = true;
    } catch (NoSuchMethodException ex) {
      // NO-OP ignore the field
    }
    return isPresent;
  }

  private boolean isJavaLang(Class<?> type) {
    if (type.isPrimitive()) {
      return true;
    } else {
      return type.getPackage().getName().startsWith("java.");
    }
  }

  /**
   * Creates a {@link ValueExpression} that wraps an object instance. This
   * method can be used to pass any object as a {@link ValueExpression}. The
   * wrapper {@link ValueExpression} is read only, and returns the wrapped
   * object via its {@code getValue()} method, optionally coerced.
   *
   * @param expression The expression to be parsed.
   * @param expectedType The type the result of the expression will be coerced
   * to after evaluation.
   * @return The parsed expression.
   */
  private ValueExpression createValueExpression(String expression, Class<?> expectedType) {
    FacesContext context = FacesContext.getCurrentInstance();
    return context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), expression, expectedType);
  }
}
