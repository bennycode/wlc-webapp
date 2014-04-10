package de.fhb.controller;

import de.fhb.config.Packages;
import de.fhb.entities.BaseEntity;
import de.fhb.service.BaseService;
import de.fhb.util.JSFUtils;
import de.fhb.view.forms.DefaultFormModel;
import de.fhb.view.forms.FormInput;
import de.fhb.view.forms.FormModel;
import de.fhb.view.forms.RenderType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.converter.SelectItemsConverter;

/**
 * @param <T>
 * @param <E>
 * @see http://docs.oracle.com/javaee/7/tutorial/doc/jsf-page002.htm#BNARF
 *
 * TODO: java.lang.ClassCastException: cannot assign instance of
 * java.lang.String to field de.fhb.controller.GenFormBaseController.backendText
 * of type java.util.ResourceBundle in instance of
 * de.fhb.controller.CategoryController.
 *
 * Example for a value expression: #{playlistController.item.category}
 */
public abstract class GenFormBaseController<T extends BaseEntity, E extends BaseService> extends BaseController<T, E> {

  private static final long serialVersionUID = 1L;
  private transient HtmlForm form;
  private final ResourceBundle backendText;
  private static final Logger LOG = Logger.getLogger(GenFormBaseController.class.getName());
  // FormModel naming convention: <EntityName>FormModel.java
  private final String FORM_MODEL_SUFFIX = "FormModel";
  private final int RESULTS_PER_PAGE = 20;

  /**
   * Note: You should never assign FacesContext as instance variable of a
   * view/session/application scoped managed bean, because the FacesContext
   * instance is request scoped.
   *
   * @see http://stackoverflow.com/a/4605163/451634
   */
  public GenFormBaseController() {
    FacesContext context = FacesContext.getCurrentInstance();
    this.backendText = context.getApplication().getResourceBundle(context, "backend");
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

    // Parse pagination from parameter
    initPagination(request.getParameter("page"));
  }

  private void initPagination(String page) {
    this.amount = RESULTS_PER_PAGE;

    if (page != null) {
      this.currentPage = Integer.valueOf(page);
      this.offset = (currentPage - 1) * this.amount;
    } else {
      this.currentPage = 1;
      this.offset = 0;
    }
  }

  public void setForm(HtmlForm form) {
    this.form = form;
  }

  public HtmlForm getForm() {
    form = new HtmlForm();
    form.setAcceptcharset("ISO-8859-1");
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

    // Add labels and properies
    Map<String, Class<?>> properties = getProperties(item);
    if (properties.size() > 0) {
      // Find Form model with reflection
      ClassLoader cl = item.getClass().getClassLoader();
      FormModel formModel;

      try {
        Class clazz = cl.loadClass(Packages.FORM_MODEL_PACKAGE + "." + item.getClass().getSimpleName() + FORM_MODEL_SUFFIX);
        formModel = (FormModel) clazz.newInstance();
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        formModel = new DefaultFormModel();
      }

      // Parse form properties
      FormInput[] parsedProperties = formModel.parseProperties(properties);

      for (FormInput property : parsedProperties) {
        form.getChildren().add(createLabel(property));
        form.getChildren().add(createComponentByType(property));
      }
    }

    form.getChildren().add(fieldsetEnd);

    HtmlCommandButton submit = createSubmitButton(FacesContext.getCurrentInstance().getApplication());
    form.getChildren().add(submit);

    return form;
  }

  private HtmlInputText createInputField(FormInput property) {
    String key = property.getKey();

    String jsfValue = String.format("#{%s.item.%s}", getControllerBeanName(), key);
    ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, property.getValue());

    HtmlInputText input = new HtmlInputText();
    input.setId(key);
    input.setValueExpression("value", valueExpression);

    if (property.isReadOnly()) {
      input.setReadonly(true);
    }

    return input;
  }

  private HtmlOutputLabel createLabel(FormInput property) {
    String text = property.getKey();

    try {
      text = backendText.getString("admin.form.label." + property.getKey());
    } catch (java.util.MissingResourceException ex) {
      LOG.log(Level.WARNING, "Missing property key for: admin.form.label.{0}", property.getKey());
    }

    HtmlOutputLabel label = new HtmlOutputLabel();
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
  private UIComponent createComponentByType(FormInput property) {
    UIComponent component;
    RenderType renderType = property.getRenderType();

    switch (renderType) {
      case DATETIME:
        initDateTimePicker();
        component = createDateInputField(property);
        break;
      case DROPDOWN:
        component = createDropdown(property);
        break;
      case LIST:
        component = createSelection(property);
        break;
      default:
        component = createInputField(property);
    }

    return component;
  }

  private HtmlInputText createDateInputField(FormInput property) {
    HtmlInputText input = createInputField(property);

    // Get locale to display locale date format
    DateTimeConverter converter = new DateTimeConverter();
    // TODO: Use local date time pattern
    converter.setPattern("dd.MM.yyyy");
    input.setConverter(converter);
    input.setStyleClass("date-time-field");

    return input;
  }

  // http://showcase.omnifaces.org/converters/SelectItemsConverter
  private HtmlSelectOneMenu createSelection(FormInput property) {
    String key = property.getKey();
    Class<?> value = property.getValue();

    String jsfValue = String.format("#{%s.item.%s}", getControllerBeanName(), key);
    ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, value);

    UISelectItems items = new UISelectItems();
    items.setValueExpression("value", valueExpression);

    HtmlSelectOneMenu menu = new HtmlSelectOneMenu();
    menu.getChildren().add(items);

    return menu;
  }

  /**
   * @see http://stackoverflow.com/a/12451778/451634
   */
  private void initDateTimePicker() {
    UIOutput js = new UIOutput();
    js.setRendererType("javax.faces.resource.Script");
    js.getAttributes().put("name", "libs/pikaday/pikaday-init.js");
    FacesContext.getCurrentInstance().getViewRoot().addComponentResource(FacesContext.getCurrentInstance(), js, "body");
  }

  private HtmlCommandButton createSubmitButton(Application app) {
    String jsfAction = String.format("#{%s.edit}", getControllerBeanName());
    MethodExpression actionExpression = JSFUtils.createMethodExpression(jsfAction, Void.class, new Class<?>[0]);

    HtmlCommandButton button = new HtmlCommandButton();
    button.setActionExpression(actionExpression);
    button.setId("save");
    button.setStyleClass("pure-button pure-button-primary");
    button.setValue("Speichern");

    return button;
  }

  private Map<String, Class<?>> getProperties(Object obj) {
    Map<String, Class<?>> attributes = new HashMap<>();

    if (obj != null) {

      Field[] objectFields = obj.getClass().getDeclaredFields();
      Field[] superclassFields = obj.getClass().getSuperclass().getDeclaredFields();

      // check for class
      for (Field field : objectFields) {
        if (checkGetterPresent(obj.getClass(), field)
                && (isJavaLang(field.getType()) || isDomainType(field.getType()))) {
//          System.out.println("Fieldname: " + field.getName() + " Fieldtype: " + field.getType());
          attributes.put(field.getName(), field.getType());
        }
      }

      // check for superclass
      for (Field field : superclassFields) {
        if (checkGetterPresent(obj.getClass().getSuperclass(), field) && isJavaLang(field.getType())) {
//          System.out.println("Fieldname: " + field.getName() + " Fieldtype: " + field.getType());
          attributes.put(field.getName(), field.getType());
        }
      }
    }

    return attributes;
  }

  private String capitalize(String line) {
    return Character.toUpperCase(line.charAt(0)) + line.substring(1);
  }

  private String lowerFirstChar(String line) {
    return Character.toLowerCase(line.charAt(0)) + line.substring(1);
  }

  private String getControllerBeanName() {
    String classname = this.getClass().getSimpleName();
    classname = lowerFirstChar(classname);
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
   * TODO: Display dropdown with all possible values from the domain type. TODO:
   * Instead string comparision of package name, we should check if the type is
   * an extension of "BaseEntity"
   *
   * @param type
   * @return
   */
  private boolean isDomainType(Class<?> type) {
    String itemPackage = type.getPackage().getName();
    String domainPackage = BaseEntity.class.getPackage().getName();

    return itemPackage.startsWith(domainPackage);
  }

  /**
   * We might need a converter between SelectItems and actual item.
   *
   * TODO: Konvertierungsfehler beim Festlegen von Wert 'Windows 7' für 'null
   * Converter'.
   *
   * @see http://showcase.omnifaces.org/converters/SelectItemsConverter
   * @see http://balusc.blogspot.de/2007/09/objects-in-hselectonemenu.html
   *
   * @param property
   * @return
   */
  @SuppressWarnings("unchecked")
  private UIComponent createDropdown(FormInput property) {
    String key = property.getKey();
    Class<?> expectedType = property.getValue();

    String beanName = lowerFirstChar(expectedType.getSimpleName()) + "Controller";
    GenFormBaseController controller = (GenFormBaseController) JSFUtils.getManagedBean(beanName);

    List<BaseEntity> list = controller.getService().findAll();
    List<SelectItem> selectItems = new ArrayList<>(list.size());
    for (BaseEntity itemInList : list) {
      selectItems.add(new SelectItem(itemInList, itemInList.getName()));
    }

    UISelectItems items = new UISelectItems();
    items.setValue(selectItems);

    HtmlSelectOneMenu menu = new HtmlSelectOneMenu();
    menu.setConverter(new SelectItemsConverter());
    menu.setId(key);
    menu.getChildren().add(items);

    String jsfValue = String.format("#{%s.item.%s}", getControllerBeanName(), key);
    ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, expectedType);
    menu.setValueExpression("value", valueExpression);

    return menu;
  }

}
