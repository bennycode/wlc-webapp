package com.welovecoding.tutorial.view.scaffolding;

import com.welovecoding.StringUtils;
import com.welovecoding.tutorial.data.base.BaseEntity;
import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.view.base.BaseController;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlMessages;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @param <T>
 * @param <E>
 * @see http://docs.oracle.com/javaee/7/tutorial/doc/jsf-page002.htm#BNARF
 *
 * TODO: java.lang.ClassCastException: cannot assign instance of
 * java.lang.String to field
 * com.welovecoding.controller.GenFormBaseController.backendText of type
 * java.util.ResourceBundle in instance of
 * com.welovecoding.controller.CategoryController.
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
  public static String ERROR_MESSAGES_NAME = "error_messages";

  /**
   * Note: You should never assign FacesContext as instance variable of a
   * view/session/application scoped managed bean, because the FacesContext
   * instance is request scoped.
   *
   * @see http://stackoverflow.com/a/4605163/451634
   */
  public GenFormBaseController() {
    LOG.setLevel(Level.FINE);

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

    CustomTag fieldset = new CustomTag("fieldset");

    HtmlMessages messages = new HtmlMessages();
    messages.setId(ERROR_MESSAGES_NAME);

    fieldset.getChildren().add(messages);

    // Add labels and properies
    Map<String, Class<?>> properties = getProperties(item);
    if (properties.size() > 0) {
      // Find Form model with reflection
      ClassLoader cl = item.getClass().getClassLoader();
      FormModel formModel;

      try {
        Class clazz = cl.loadClass("com.welovecoding.tutorial.view." + item.getClass().getSimpleName().toLowerCase() + "." + item.getClass().getSimpleName() + FORM_MODEL_SUFFIX);
        formModel = (FormModel) clazz.newInstance();
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        formModel = new DefaultFormModel();
      }

      // Parse form properties
      FormInput[] parsedProperties = formModel.parseProperties(properties);

      for (FormInput property : parsedProperties) {
        fieldset.getChildren().add(componentFactory.createLabel(property));
        fieldset.getChildren().add(componentFactory.createComponentByType(property));
      }
    }

    form.getChildren().add(fieldset);

    HtmlCommandButton submit = componentFactory.createSubmitButton(FacesContext.getCurrentInstance().getApplication());
    form.getChildren().add(submit);

    return form;
  }

  private Map<String, Class<?>> getProperties(Object obj) {
    Map<String, Class<?>> attributes = new HashMap<>();

    if (obj != null) {
      Map<String, Class<?>> directMembers = getProperties(obj.getClass());
      Map<String, Class<?>> superclassMembers = getProperties(obj.getClass().getSuperclass());

      attributes.putAll(directMembers);
      attributes.putAll(superclassMembers);
    }

    return attributes;
  }

  private Map<String, Class<?>> getProperties(Class<?> aClass) {
    Map<String, Class<?>> attributes = new HashMap<>();
    Field[] objectFields = aClass.getDeclaredFields();

    for (Field field : objectFields) {
      if (checkGetterPresent(aClass, field)
              && (isJavaLang(field.getType()) || isDomainType(field.getType()))) {
        LOG.log(Level.FINE, "Mapped member: {0} ({1})", new Object[]{field.getName(), field.getType()});
        attributes.put(field.getName(), field.getType());
      } else {
        LOG.log(Level.FINEST, "This member will not be mapped: {0} ({1})", new Object[]{field.getName(), field.getType()});
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
    //TODO HERES THE BUG...THE ENTITIES ARE NO LONGER IN THE SAME PACKAGE AS THE BASEENTITY!!!
    // Quickfix: Assume that all entities with e.g. com.welovecoding are entities of the project (call me if u have a better and easier idea ;P)
    String[] packages = BaseEntity.class.getPackage().getName().split("\\.");
    String domainPackage = packages[0] + "." + packages[1];

    return itemPackage.startsWith(domainPackage);
  }
}
