package de.fhb.controller;

import de.fhb.entities.BaseEntity;
import de.fhb.service.BaseService;
import de.fhb.util.JSFUtils;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.servlet.http.HttpServletRequest;

/**
 * @param <T>
 * @param <E>
 * @see http://docs.oracle.com/javaee/7/tutorial/doc/jsf-page002.htm#BNARF
 */
public abstract class GenFormBaseController<T extends BaseEntity, E extends BaseService> extends BaseController<T, E> {

  private static final long serialVersionUID = 1L;
  private transient HtmlForm form;
  private final FacesContext context;
  private final Application app;
  private final ResourceBundle backendText;
  private static final Logger LOG = Logger.getLogger(GenFormBaseController.class.getName());

  public GenFormBaseController() {
    this.context = FacesContext.getCurrentInstance();
    this.app = context.getApplication();
    this.backendText = app.getResourceBundle(context, "backend");
  }

  public void setForm(HtmlForm form) {
    this.form = form;
  }

  public HtmlForm getForm() {
    form = (HtmlForm) app.createComponent(HtmlForm.COMPONENT_TYPE);
    form.setStyleClass("pure-form pure-form-stacked");

    UIOutput legend = new UIOutput();
    legend.setRendererType("javax.faces.Text");
    legend.getAttributes().put("escape", false);
    legend.setValue("<legend>Eintrag bearbeiten</legend>");

    // Create a <fieldset>
    HtmlOutputText fieldsetStart = new HtmlOutputText();
    fieldsetStart.setEscape(false);
    fieldsetStart.setValue("<fieldset>");

    HtmlOutputText fieldsetEnd = new HtmlOutputText();
    fieldsetEnd.setEscape(false);
    fieldsetEnd.setValue("</fieldset>");

    form.getChildren().add(fieldsetStart);

    if (item != null) {
      for (Map.Entry<String, Class<?>> property : getAttributes(item).entrySet()) {
        // Labels
        HtmlOutputLabel label = createLabel(property);
        form.getChildren().add(label);

        // Input fields
        UIComponent component = getComponentByType(property);
        form.getChildren().add(component);
      }
    }

    form.getChildren().add(fieldsetEnd);

    HtmlCommandButton submit = createSubmitButton(app);
    form.getChildren().add(submit);

    return form;
  }

  private HtmlInputText createInputField(Map.Entry<String, Class<?>> property) {
    String jsfValue = String.format("#{%s.item.%s}", getELClassname(), property.getKey());
    ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, property.getValue());

    HtmlInputText input = (HtmlInputText) app.createComponent(HtmlInputText.COMPONENT_TYPE);
    input.setId(property.getKey());
    input.setValueExpression("value", valueExpression);

    return input;
  }

  private HtmlOutputLabel createLabel(Map.Entry<String, Class<?>> property) {
    String text = property.getKey();

    try {
      text = backendText.getString("admin.form.label." + property.getKey());
    } catch (java.util.MissingResourceException ex) {
      LOG.log(Level.WARNING, "Missing property key for: admin.form.label.{0}", property.getKey());
    }

    HtmlOutputLabel label = (HtmlOutputLabel) app.createComponent(HtmlOutputLabel.COMPONENT_TYPE);
    label.setValue(text);

    return label;
  }

  /**
   * Checks the classname of the property and creates (dependant on the
   * classname) a corresponding UIComponent (HTML tag / DOM element) for that
   * property.
   *
   * @param property
   * @return
   */
  private UIComponent getComponentByType(Map.Entry<String, Class<?>> property) {
    UIComponent component;
    String className = property.getValue().getName();

    switch (className) {
      case "java.util.Date":
        component = createDateInputField(property);
        break;
      default:
        component = createInputField(property);
    }

    return component;
  }

  private HtmlInputText createDateInputField(Map.Entry<String, Class<?>> property) {
    HtmlInputText input = createInputField(property);

    // Get locale to display locale date format
    DateTimeConverter converter = (DateTimeConverter) app.createConverter(DateTimeConverter.CONVERTER_ID);
    // TODO: Use local date time pattern
    converter.setPattern("dd.MM.yyyy");
    input.setConverter(converter);

    return input;
  }

  private String getLocalDateTimePattern() {
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    Locale locale = request.getLocale();
    DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    return ((SimpleDateFormat) formatter).toPattern();
  }

  private HtmlCommandButton createSubmitButton(Application app) {
    String jsfAction = String.format("#{%s.edit}", getELClassname());
    MethodExpression actionExpression = JSFUtils.createMethodExpression(jsfAction, Void.class, new Class<?>[0]);

    /*
     <button type="submit" 
     class="pure-button pure-button-primary"
     jsf:action="#{authorController.edit}"
     >Speichern</button>
     */
    HtmlCommandButton button = (HtmlCommandButton) app.createComponent(HtmlCommandButton.COMPONENT_TYPE);
    button.setActionExpression(actionExpression);
    button.setId("save");
    button.setStyleClass("pure-button pure-button-primary");
    button.setValue("Speichern");

    return button;
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
