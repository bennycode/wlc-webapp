package com.welovecoding.tutorial.view.scaffolding;

import com.welovecoding.StringUtils;
import com.welovecoding.tutorial.data.base.BaseEntity;
import com.welovecoding.tutorial.view.JSFUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @see http://www.mkyong.com/jsf2/custom-converter-in-jsf-2-0/
 * @see http://www.mastertheboss.com/jsf/develop-custom-jsf-converters
 */
public class DropdownItemsConverter implements Converter {

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

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value == null) {
      return null;
    }
    return value.toString();
  }
}
