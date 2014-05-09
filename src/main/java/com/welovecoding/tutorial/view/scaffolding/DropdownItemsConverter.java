package com.welovecoding.tutorial.view.scaffolding;

import com.welovecoding.StringUtils;
import com.welovecoding.tutorial.data.base.BaseEntity;
import com.welovecoding.tutorial.view.JSFUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @see http://www.mkyong.com/jsf2/custom-converter-in-jsf-2-0/
 * @see http://www.mastertheboss.com/jsf/develop-custom-jsf-converters
 */
@FacesConverter("wlc.DropdownItemsConverter")
public class DropdownItemsConverter implements Converter {

  /**
   *
   * @param context The currenct JSF context.
   * @param component An instance of the UI component, that holds the values.
   * Example: An instance of HtmlSelectOneMenu
   * @param value The value of the dropdown option. Example: Category[id=1]
   * Element: <option value="Category[id=2]">Java</option>
   * @return A base entity, Example: An instance of Category.class
   */
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }

    String controllerBeanName = StringUtils.getControllerBeanNameByStringValue(value);
    Long id = StringUtils.getIdByStringValue(value);

    GenFormBaseController controller = (GenFormBaseController) JSFUtils.getManagedBean(controllerBeanName);
    BaseEntity object = controller.getService().find(id);

    return object;
  }

  /**
   *
   * @param context The currenct JSF context.
   * @param component An instance of the UI component, that holds the values.
   * Example: An instance of HtmlSelectOneMenu
   * @param value The object that we got in "getAsObject". Example: An instance
   * of Category.class
   * @return
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value == null) {
      return null;
    }

    return value.toString();
  }
}
