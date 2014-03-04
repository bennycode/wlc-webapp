/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.controller;

import de.fhb.entities.BaseEntity;
import de.fhb.service.BaseService;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

/**
 *
 * @author MacYser
 */
public abstract class GenFormBaseController<T extends BaseEntity, E extends BaseService> extends BaseController<T, E> {

  private static final long serialVersionUID = 1L;

  private transient HtmlForm form;
  protected final String FORM_STYLE_CLASS = "pure-form pure-form-aligned";
  protected final String PANEL_GRID_STYLE_CLASS = "panelgrid";
  protected final String PANEL_GRID_COLUMN_STYLE_CLASS = "panelgridcolumn";
  protected final String PANEL_GRID_ROW_STYLE_CLASS = "pure-control-group";
  protected final String OUTPUT_TEXT_STYLE_CLASS = "outputtext";
  protected final String INPUT_TEXT_STYLE_CLASS = "inputtext";

  public void setForm(HtmlForm form) {
    this.form = form;
  }

  public HtmlForm getForm() {
    Application app = FacesContext.getCurrentInstance().getApplication();
    form = (HtmlForm) app.createComponent(HtmlForm.COMPONENT_TYPE);
    form.setStyleClass(FORM_STYLE_CLASS);

    HtmlPanelGrid panelGrid = (HtmlPanelGrid) app.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
    panelGrid.setStyleClass(PANEL_GRID_STYLE_CLASS);
    panelGrid.setColumnClasses(PANEL_GRID_COLUMN_STYLE_CLASS);
    panelGrid.setRowClasses(PANEL_GRID_ROW_STYLE_CLASS);
    form.getChildren().add(panelGrid);
    panelGrid.setColumns(2);

    if (item != null) {
      for (Map.Entry<String, Class<?>> entry : getAttributes(item).entrySet()) {
        HtmlOutputText key = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
        key.setStyleClass(OUTPUT_TEXT_STYLE_CLASS);
        key.setValue(capitalize(entry.getKey()));
        panelGrid.getChildren().add(key);

        HtmlInputText value = (HtmlInputText) app.createComponent(HtmlInputText.COMPONENT_TYPE);
        value.setStyleClass(INPUT_TEXT_STYLE_CLASS);
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
