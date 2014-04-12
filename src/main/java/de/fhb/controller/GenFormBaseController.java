package de.fhb.controller;

import de.fhb.config.Packages;
import de.fhb.entities.BaseEntity;
import de.fhb.service.BaseService;
import de.fhb.util.StringUtils;
import de.fhb.view.forms.DefaultFormModel;
import de.fhb.view.forms.FormInput;
import de.fhb.view.forms.FormModel;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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

  private transient HtmlForm form;

  private static final Logger LOG = Logger.getLogger(GenFormBaseController.class.getName());
  // FormModel naming convention: <EntityName>FormModel.java
  private final String FORM_MODEL_SUFFIX = "FormModel";
  //TODO knowledge about pagination should be in BaseController
  private final int RESULTS_PER_PAGE = 20;
  private final ComponentFactory componentFactory;

  /**
   * Note: You should never assign FacesContext as instance variable of a
   * view/session/application scoped managed bean, because the FacesContext
   * instance is request scoped.
   *
   * @see http://stackoverflow.com/a/4605163/451634
   */
  public GenFormBaseController() {
    componentFactory = new ComponentFactory(StringUtils.lowerFirstChar(this.getClass().getSimpleName()));
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

    // Parse pagination from parameter
    initPagination(request.getParameter("page"));
  }

  //TODO this should be in BaseController instead of GenFormBaseController
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

    // TODO START never used
    UIOutput legend = new UIOutput();
    legend.setRendererType("javax.faces.Text");
    legend.getAttributes().put("escape", false);
    legend.setValue("<legend>Eintrag bearbeiten</legend>");
    // TODO END never used

    //TODO fieldset should be in ComponentFactory
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
        form.getChildren().add(componentFactory.createLabel(property));
        form.getChildren().add(componentFactory.createComponentByType(property));
      }
    }

    form.getChildren().add(fieldsetEnd);

    HtmlCommandButton submit = componentFactory.createSubmitButton(FacesContext.getCurrentInstance().getApplication());
    form.getChildren().add(submit);

    return form;
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
          System.out.println("Fieldname: " + field.getName() + " Fieldtype: " + field.getType());
          attributes.put(field.getName(), field.getType());
        }
      }

      // check for superclass
      for (Field field : superclassFields) {
        if (checkGetterPresent(obj.getClass().getSuperclass(), field) && isJavaLang(field.getType())) {
          System.out.println("Fieldname: " + field.getName() + " Fieldtype: " + field.getType());
          attributes.put(field.getName(), field.getType());
        }
      }
    }

    return attributes;
  }

  private boolean checkGetterPresent(Class<?> clazz, Field field) {
    Class<?>[] emptyParamObjects = new Class<?>[]{};
    boolean isPresent = false;
    try {
      clazz.getDeclaredMethod("get" + StringUtils.capitalize(field.getName()), emptyParamObjects);
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
}
