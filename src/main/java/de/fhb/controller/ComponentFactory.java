package de.fhb.controller;

import de.fhb.entities.BaseEntity;
import de.fhb.util.JSFUtils;
import de.fhb.util.StringUtils;
import de.fhb.view.forms.DropdownItemsConverter;
import de.fhb.view.forms.FormInput;
import de.fhb.view.forms.RenderType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author Michael Koppen
 */
public class ComponentFactory {

  private static final Logger LOG = Logger.getLogger(ComponentFactory.class.getName());

  private final ResourceBundle backendText;
  private final String controllerBeanName;

  public ComponentFactory(String controllerBeanName) {
    this.controllerBeanName = controllerBeanName;
    FacesContext context = FacesContext.getCurrentInstance();
    this.backendText = context.getApplication().getResourceBundle(context, "backend");
  }

  /**
   * Checks the classname of the property and creates (dependant on the
   * classname) a corresponding UIComponent (HTML tag / DOM element) for that
   * property.
   *
   * @param property
   * @return
   */
  public UIComponent createComponentByType(FormInput property) {
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
      case ENUM:
        component = createDropdownForEnums(property);
        break;
      case LIST:
        component = createSelection(property);
        break;
      case TEXTAREA:
        component = createTextarea(property);
        break;
      default:
        component = createInputField(property);
    }

    return component;
  }

  private HtmlSelectOneMenu createDropdownForEnums(FormInput property) {
    String key = property.getKey();
    Class<?> expectedType = property.getValue();

    List<?> constants = Arrays.asList(expectedType.getEnumConstants());
    UISelectItems items = new UISelectItems();

    List<SelectItem> selectItems = new ArrayList<>(constants.size());
    for (Object constant : constants) {
      selectItems.add(new SelectItem(constant, constant.toString()));
    }

    items.setValue(selectItems);

    HtmlSelectOneMenu menu = new HtmlSelectOneMenu();
    menu.setId(key);
    menu.getChildren().add(items);

    String jsfValue = String.format("#{%s.item.%s}", controllerBeanName, key);
    ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, expectedType);
    menu.setValueExpression("value", valueExpression);
    return menu;
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
  private HtmlSelectOneMenu createDropdown(FormInput property) {
    String key = property.getKey();
    Class<?> expectedType = property.getValue();

    String beanName = StringUtils.getControllerBeanName(expectedType.getSimpleName());
    GenFormBaseController controller = (GenFormBaseController) JSFUtils.getManagedBean(beanName);

    List<BaseEntity> list = controller.getService().findAll();
    List<SelectItem> selectItems = new ArrayList<>(list.size());
    for (BaseEntity itemInList : list) {
      selectItems.add(new SelectItem(itemInList, itemInList.getName()));
    }

    UISelectItems items = new UISelectItems();
    items.setValue(selectItems);

    HtmlSelectOneMenu menu = new HtmlSelectOneMenu();
    menu.setConverter(new DropdownItemsConverter());
    menu.setId(key);
    menu.getChildren().add(items);

    String jsfValue = String.format("#{%s.item.%s}", controllerBeanName, key);
    ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, expectedType);
    menu.setValueExpression("value", valueExpression);

    return menu;
  }

  private UIComponent createTextarea(FormInput property) {
    String key = property.getKey();

    String jsfValue = String.format("#{%s.item.%s}", controllerBeanName, key);
    ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, property.getValue());

    HtmlInputTextarea input = new HtmlInputTextarea();
    input.setId(key);
    input.setValueExpression("value", valueExpression);

    return input;
  }

  // http://showcase.omnifaces.org/converters/SelectItemsConverter
  private HtmlSelectOneMenu createSelection(FormInput property) {
    String key = property.getKey();
    Class<?> value = property.getValue();

    String jsfValue = String.format("#{%s.item.%s}", controllerBeanName, key);
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

  public HtmlCommandButton createSubmitButton(Application app) {
    String jsfAction = String.format("#{%s.edit}", controllerBeanName);
    MethodExpression actionExpression = JSFUtils.createMethodExpression(jsfAction, Void.class, new Class<?>[0]);

    HtmlCommandButton button = new HtmlCommandButton();
    button.setActionExpression(actionExpression);
    button.setId("save");
    button.setStyleClass("pure-button pure-button-primary");
    button.setValue("Speichern");

    return button;
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

  private HtmlInputText createInputField(FormInput property) {
    String key = property.getKey();

    String jsfValue = String.format("#{%s.item.%s}", controllerBeanName, key);
    ValueExpression valueExpression = JSFUtils.createValueExpression(jsfValue, property.getValue());

    HtmlInputText input = new HtmlInputText();
    input.setId(key);
    input.setValueExpression("value", valueExpression);

    if (property.isReadOnly()) {
      input.setReadonly(true);
    }

    return input;
  }

  public HtmlOutputLabel createLabel(FormInput property) {
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

}
