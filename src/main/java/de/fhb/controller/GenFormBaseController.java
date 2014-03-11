package de.fhb.controller;

import de.fhb.entities.Author;
import de.fhb.entities.BaseEntity;
import de.fhb.service.BaseService;
import de.fhb.util.JSFUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import org.apache.myfaces.custom.fieldset.Fieldset;

/**
 * @author MacYser
 * @see http://docs.oracle.com/javaee/7/tutorial/doc/jsf-page002.htm#BNARF
 */
public abstract class GenFormBaseController<T extends BaseEntity, E extends BaseService> extends BaseController<T, E> {

  private static final long serialVersionUID = 1L;
  private transient HtmlForm form;

  public void setForm(HtmlForm form) {
    this.form = form;
  }

  public HtmlForm getForm() {
    FacesContext context = FacesContext.getCurrentInstance();
    Application app = context.getApplication();
    form = (HtmlForm) app.createComponent(HtmlForm.COMPONENT_TYPE);
    form.setStyleClass("pure-form pure-form-stacked");

    UIOutput legend = new UIOutput();
    legend.setRendererType("javax.faces.Text");
    legend.getAttributes().put("escape", false);
    legend.setValue("<legend>Eintrag bearbeiten</legend>");

    HtmlOutputText linebreak = new HtmlOutputText();
    linebreak.setValue("<br/>");
    linebreak.setEscape(false);

    // Create form fields from entity (business model) 
    Fieldset fieldset = new Fieldset();
    fieldset.getChildren().add(legend);

    if (item != null) {
      for (Map.Entry<String, Class<?>> property : getAttributes(item).entrySet()) {
        // Label
        HtmlOutputLabel label = (HtmlOutputLabel) app.createComponent(HtmlOutputLabel.COMPONENT_TYPE);
        label.setValue(property.getKey());
        fieldset.getChildren().add(label);

        // Input
        /*
         <input 
         -                id="name" 
         -                type="text" 
         -                placeholder="Konrad Zuse" 
         -                required="required"
         -                jsf:id="name"
         -                jsf:value="#{authorController.item.name}"
         -                />
         */
        String jsfValue = String.format("#{%s.item.%s}", getELClassname(), property.getKey());
        ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, property.getValue());

        HtmlInputText input = (HtmlInputText) app.createComponent(HtmlInputText.COMPONENT_TYPE);
        input.setId(property.getKey());
        input.setValueExpression("value", valueExpression);

        fieldset.getChildren().add(input);
      }
    }

    /*
     <button type="submit" 
     class="pure-button pure-button-primary"
     jsf:action="#{authorController.edit}"
     >Speichern</button>
     */
    String jsfAction = String.format("#{%s.edit}", getELClassname());
    MethodExpression actionExpression = JSFUtils.createMethodExpression(jsfAction, null, new Class[]{});

    HtmlCommandButton button = (HtmlCommandButton) app.createComponent(HtmlCommandButton.COMPONENT_TYPE);
    button.setStyleClass("pure-button pure-button-primary");
    button.setValue("Speichern");
    button.setActionExpression(actionExpression);

    form.getChildren().add(fieldset);
    // TODO: Button action does not work yet :(
    // form.getChildren().add(button);

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

  /**
   * @deprecated A Property file will translate all keys and provide capital
   * letters.
   * @param line
   * @return
   */
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

}
