package com.welovecoding.tutorial.view;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.convert.Converter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A collection of utility methods that handle repetitive boilerplate code.
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.6.1
 */
public class JSFUtils implements Serializable {

  private static final long serialVersionUID = 4005663315445526130L;
  private static final Logger LOG = Logger.getLogger(JSFUtils.class.getName());

  public static void addErrorMessage(String msg) {
    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
  }

  public static void addSuccessMessage(String msg) {
    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
    FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
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
   * @see #createValueExpression(java.lang.String)
   */
  public static ValueExpression createValueExpression(final String expression, Class<?> expectedType) {
    FacesContext context = FacesContext.getCurrentInstance();
    return context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), expression, expectedType);
  }

  /**
   * Creates a {@link ValueExpression} that wraps an object instance. This
   * method can be used to pass any object as a {@link ValueExpression}. The
   * wrapper {@link ValueExpression} is read only, and returns the wrapped
   * object via its {@code getValue()} method, optionally coerced.
   *
   * @param expression The expression to be parsed.
   * @return The parsed expression.
   * @since 1.4
   * @see #createValueExpression(java.lang.String, java.lang.Class)
   */
  public static ValueExpression createValueExpression(final String expression) {
    FacesContext context = FacesContext.getCurrentInstance();
    return context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), expression, Object.class);
  }

  /**
   * <p>
   * This convenience method sets a {@link ValueExpression} to a specific
   * value.</p>
   * <p>
   * An example, {@literal #{currentDate}} could be mapped to the using
   * {@literal JSFUtils.setValueExpressionToValue(new Date(System.currentTimeMillis), "#{currentDate}")}.
   * This is useful when setting {@literal <f:param />} values.</p>
   *
   * @param value The {@code Object} to set as the {@link ValueExpression}
   * target.
   * @param expression The expression to be parsed.
   * @since 1.4
   * @see #createValueExpression(java.lang.String, java.lang.Class)
   */
  public static void setValueExpressionToValue(final Object value, final String expression) {
    ValueExpression ve = createValueExpression(expression);
    ve.setValue(FacesContext.getCurrentInstance().getELContext(), value);
  }

  /**
   * <p>
   * This convenience method is used to map a variable to a
   * {@link ValueExpression}.</p>
   * <p>
   * An example usage could be using the value from a list and mapping it to a
   * variable, e.g.,
   * {@literal JSFUtils.mapVariableToValueExpression("Phone", "#{directory.listings['Office']}")}.
   * </p>
   *
   * @param variable The variable name to map.
   * @param expression The expression to be mapped to the variable.
   * @since 1.4
   *
   */
  public static void mapVariableToValueExpression(final String variable, final String expression) {
    ValueExpression ve = createValueExpression(expression);
    FacesContext.getCurrentInstance().getELContext().getVariableMapper().setVariable(variable, ve);
  }

  /**
   * This is a convenience method that parses an expression into a
   * {@link MethodExpression} for later evaluation. Use this method for
   * expressions that refer to methods. If the expression is a {@code String}
   * literal, a {@link MethodExpression} is created, which when invoked, returns
   * the {@code String} literal, coerced to expectedReturnType. An
   * {@link ELException} is thrown if expectedReturnType is {@code void} or if
   * the coercion of the {@code String} literal to the expectedReturnType yields
   * an error. This method should perform syntactic validation of the
   * expression. If in doing so it detects errors, it should raise an
   * {@link ELException}.
   *
   * @param methodExpression The expression to parse.
   * @param expectedReturnType The expected return type for the method to be
   * found. After evaluating the expression, the {@link MethodExpression} must
   * check that the return type of the actual method matches this type. Passing
   * in a value of {@code null} indicates the caller does not care what the
   * return type is, and the check is disabled.
   * @param expectedParamTypes The expected parameter types for the method to be
   * found. Must be an array with no elements if there are no parameters
   * expected. It is illegal to pass {@code null}, unless the method is
   * specified with arguments in the EL expression, in which case these
   * arguments are used for method selection, and this parameter is ignored.
   * @return The parsed expression.
   */
  public static MethodExpression createMethodExpression(final String methodExpression, Class<?> expectedReturnType, Class<?>[] expectedParamTypes) {
    FacesContext context = FacesContext.getCurrentInstance();
    return context.getApplication().getExpressionFactory()
            .createMethodExpression(context.getELContext(), methodExpression, expectedReturnType, expectedParamTypes);
  }

  /**
   * This is a convenience method that produces an {@link MethodExpression} to
   * handle an {@link ActionEvent}.
   *
   * @param expression The expression to be parsed.
   * @return The parsed expression.
   * @since 1.4
   * @see #createMethodExpression(java.lang.String, java.lang.Class,
   * java.lang.Class<?>[])
   */
  public static MethodExpression createActionEventMethodExpression(final String expression) {
    Class<?>[] expectedParamTypes = new Class<?>[1];
    expectedParamTypes[0] = ActionEvent.class;
    return createMethodExpression(expression, Void.TYPE, expectedParamTypes);
  }

  /**
   * This convenience method creates a {@link MethodExpression} for use as an
   * action.
   *
   * This is used to set the {@link MethodExpression} pointing at the
   * application action to be invoked, on a {@link UIComponent} activated by the
   * user, during the Apply Request Values or Invoke Application phase of the
   * request processing lifecycle, depending on the value of the immediate
   * property.
   *
   * Any method referenced by such an expression must be public, with a return
   * type of {@code String}, and accept no parameters.
   *
   * @param expression The expression to be parsed.
   * @return An expression that has a {@code String} return value and no
   * parameters.
   * @since 1.4
   * @see #createMethodExpression(java.lang.String, java.lang.Class,
   * java.lang.Class<?>[])
   */
  public static MethodExpression createActionExpression(final String expression) {
    return createMethodExpression(expression, String.class, new Class<?>[]{});
  }

  /**
   * This is a convenience method that produces a {@link ActionListener} for a
   * {@link MethodExpression}
   *
   * @param methodExpression The expression to parse.
   * @param expectedReturnType The expected return type for the method to be
   * found. After evaluating the expression, the {@link MethodExpression} must
   * check that the return type of the actual method matches this type. Passing
   * in a value of {@code null} indicates the caller does not care what the
   * return type is, and the check is disabled.
   * @param expectedParamTypes The expected parameter types for the method to be
   * found. Must be an array with no elements if there are no parameters
   * expected. It is illegal to pass {@code null}, unless the method is
   * specified with arguments in the EL expression, in which case these
   * arguments are used for method selection, and this parameter is ignored.
   * @return an {@link ActionListener} that wraps a {@link
   * MethodExpression}. When it receives a {@link ActionEvent}, it executes a
   * method on an object identified by the {@link
   * MethodExpression}.
   * @since 1.4
   * @see #createMethodExpression(java.lang.String, java.lang.Class,
   * java.lang.Class<?>[])
   */
  public static MethodExpressionActionListener createMethodExpressionActionListener(final String methodExpression, Class<?> expectedReturnType, Class<?>[] expectedParamTypes) {
    return new MethodExpressionActionListener(createMethodExpression(methodExpression, expectedReturnType, expectedParamTypes));
  }

  /**
   * Return a typed reference to the bean from the
   * {@link javax.faces.context.ExternalContext#getApplicationMap()}.
   *
   * @param <T> type of class.
   * @param beanName name of the class to search for in
   * {@link javax.faces.ExternalContext#getApplicationMap()}.
   * @return referenced bean.
   * @since 1.4
   * @see #getBean(java.lang.String)
   * @see #getBean(java.lang.String, java.lang.Class)
   */
  @SuppressWarnings("unchecked")
  public static <T> T getTypedBean(String beanName) {
    return (T) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(beanName);
  }

  /**
   * Return a reference to the bean from the
   * {@link javax.faces.context.ExternalContext#getApplicationMap()}.
   *
   * @param beanName name of the class to search for in
   * {@link javax.faces.ExternalContext#getApplicationMap()}.
   * @return referenced bean.
   * @since 1.4
   * @see #getTypedBean(java.lang.String)
   * @see #getBean(java.lang.String, java.lang.Class)
   */
  public static Object getBean(String beanName) {
    return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(beanName);
  }

  /**
   * @param beanName
   * @return
   * @see
   * http://www.javacodegeeks.com/2012/04/5-useful-methods-jsf-developers-should.html
   */
  public static Object getManagedBean(final String beanName) {
    FacesContext fc = FacesContext.getCurrentInstance();
    Object bean;

    try {
      ELContext elContext = fc.getELContext();
      bean = elContext.getELResolver().getValue(elContext, null, beanName);
    } catch (RuntimeException e) {
      throw new FacesException(e.getMessage(), e);
    }

    if (bean == null) {
      throw new FacesException("Managed bean with name '" + beanName
              + "' was not found. Check your faces-config.xml or @ManagedBean annotation.");
    }

    return bean;
  }

  /**
   * Return a reference to the bean from the
   * {@link javax.faces.context.FacesContext#getCurrentInstance()}.
   *
   * @param <T> type of class.
   * @param beanName name of the class to search for in
   * {@link javax.faces.context.FacesContext}.
   * @param beanClass class type of bean to return.
   * @return referenced bean.
   * @since 1.4
   * @see #getBean(java.lang.String)
   * @see #getTypedBean(java.lang.String)
   */
  public static <T> T getBean(String beanName, Class<T> beanClass) {
    FacesContext context = FacesContext.getCurrentInstance();
    Application application = context.getApplication();
    return (T) application.evaluateExpressionGet(context, "#{" + beanName + "}", beanClass);
  }

  /**
   * This method provides a convenient means of determining the JSF
   * Specification version.
   *
   * @return JSF Specification version, e.g. 2.1
   * @since 1.5
   */
  public static String getSpecificationVersion() {
    return FacesContext.getCurrentInstance().getClass().getPackage().getSpecificationVersion();
  }

  /**
   * This method provides a convenient means of determining the JSF
   * Implementation version.
   *
   * @return JSF Implementation version, e.g. 2.1.26
   * @since 1.5
   */
  public static String getImplementationVersion() {
    return FacesContext.getCurrentInstance().getClass().getPackage().getImplementationVersion();
  }

  public static String getRequestParameter(String key) {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
  }

  public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
    String theId = getRequestParameter(requestParameterName);
    return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
  }

  /**
   * This method provides a convenient means of determining the JSF
   * Implementation Title.
   *
   * @return JSF implementation title, e.g. Mojarra.
   * @since 1.5
   */
  public static String getImplementationTitle() {
    return FacesContext.getCurrentInstance().getClass().getPackage().getImplementationTitle();
  }

  /**
   * This method sets the bean name provided to {@code null}.
   *
   * @param beanName The name of the bean to remove.
   * @since 1.6
   */
  public static void removeManagedBean(final String beanName) {
    FacesContext fc = FacesContext.getCurrentInstance();
    fc.getELContext().getELResolver().setValue(fc.getELContext(), null, beanName, null);
  }

  /**
   * Programmatic method to create an &lt;h:commandLink/&gt;.
   *
   * @param context The current request context.
   * @param expression The EL expression to be parsed and set. The expression
   * must comply with the rules for an action expression.
   * @param value The output value of the component.
   * @return A complete {@link HtmlCommandLink} component.
   * @since 1.1
   * @see #createActionExpression(java.lang.String)
   */
  public static HtmlCommandLink createHtmlCommandLink(final FacesContext context, final String expression, final String value) {
    Application application = context.getApplication();
    HtmlCommandLink htmlCommandLink = (HtmlCommandLink) application.createComponent(HtmlCommandLink.COMPONENT_TYPE);
    htmlCommandLink.setValue(value);
    htmlCommandLink.setActionExpression(createActionExpression(expression));
    return htmlCommandLink;
  }

  /**
   * Programmatic method to create an &lt;h:commandButton/&gt;.
   *
   * @param context The current request context.
   * @param expression The EL expression to be parsed and set. The expression
   * must comply with the rules for an action expression.
   * @param value The output value of the component.
   * @return A complete {@link HtmlCommandButton} component.
   * @since 1.2
   * @see #createActionExpression(java.lang.String)
   */
  public static HtmlCommandButton createHtmlCommandButton(final FacesContext context, final String expression, final String value) {
    Application application = context.getApplication();
    HtmlCommandButton htmlCommandButton = (HtmlCommandButton) application.createComponent(HtmlCommandButton.COMPONENT_TYPE);
    htmlCommandButton.setValue(value);
    htmlCommandButton.setActionExpression(createActionExpression(expression));
    return htmlCommandButton;
  }

  public static Locale getLocaleFromDomain(final FacesContext facesContext) {
    Locale locale = Locale.ENGLISH;

    Object request = facesContext.getExternalContext().getRequest();
    if (request instanceof HttpServletRequest) {
      HttpServletRequest servletRequest = (HttpServletRequest) request;

      String forwardedHost = servletRequest.getHeader("x-forwarded-host");
      String directHost = servletRequest.getHeader("host");
      String host = directHost;

      if (forwardedHost != null) {
        host = forwardedHost;
      }

      if (host.endsWith(".de")) {
        locale = Locale.GERMAN;
      }
    }

    return locale;
  }

  /**
   * <p>
   * Determines the Base URL, e.g.,
   * {@literal http://localhost:8080/myApplication} from the
   * {@link FacesContext}.</p>
   *
   * @param facesContext The {@link FacesContext} to examine.
   * @return the base URL.
   * @since 1.3
   */
  public static String getBaseURL(final FacesContext facesContext) {
    return getBaseURL(facesContext.getExternalContext());
  }

  /**
   * <p>
   * Determines the Base URL, e.g.,
   * {@literal http://localhost:8080/myApplication} from the
   * {@link ExternalContext}.</p>
   *
   * @param externalContext The {@link ExternalContext} to examine.
   * @return the base URL.
   * @since 1.3
   */
  public static String getBaseURL(final ExternalContext externalContext) {
    try {
      return getBaseURL((HttpServletRequest) externalContext.getRequest());
    } catch (MalformedURLException ex) {
      Logger.getLogger(JSFUtils.class.getName()).log(Level.SEVERE, null, ex);
      return "";
    }
  }

  /**
   * <p>
   * Determines the Base URL, e.g.,
   * {@literal http://localhost:8080/myApplication} from the
   * {@link HttpServletRequest}.</p>
   *
   * @param request The {@link HttpServletRequest} to examine.
   * @return the base URL.
   * @throws MalformedURLException if an exception occurs during parsing of the
   * URL.
   * @see URL
   * @since 1.3
   */
  public static String getBaseURL(final HttpServletRequest request) throws MalformedURLException {
    String[] domainParts = request.getHeader("Host").split(":");

    URL url = new URL(request.getScheme(),
            domainParts[0],
            request.getServerPort(),
            request.getContextPath());

    return url.toString();
  }

  /**
   * Provides access to FacesContext out of Servlets and Filters.
   *
   * @param request
   * @param response
   * @return
   */
  public static FacesContext getFacesContext(HttpServletRequest request, HttpServletResponse response) {
    // Get current FacesContext.
    FacesContext facesContext = FacesContext.getCurrentInstance();

    // Check current FacesContext.
    if (facesContext == null) {

      // Create new Lifecycle.
      LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
      Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

      // Create new FacesContext.
      FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
      facesContext = contextFactory.getFacesContext(
              request.getSession().getServletContext(), request, response, lifecycle);

      // Create new View.
      UIViewRoot view = facesContext.getApplication().getViewHandler().createView(
              facesContext, "");
      facesContext.setViewRoot(view);

      // Set current FacesContext.
      FacesContextWrapper.setCurrentInstance(facesContext);
    }

    return facesContext;
  }

  /**
   * Helper class for JSFUtils#getFacesContext()
   */
  private static abstract class FacesContextWrapper extends FacesContext {

    protected static void setCurrentInstance(FacesContext facesContext) {
      FacesContext.setCurrentInstance(facesContext);
    }
  }
}
