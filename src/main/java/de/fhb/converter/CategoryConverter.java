package de.fhb.converter;

import de.fhb.entities.Category;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("CategoryConverter")
public class CategoryConverter implements Converter {

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    Category item = (Category) value;
    String key = CategoryConverter.class.getName() + item.getId();
    context.getExternalContext().getSessionMap().put(key, item);
    System.out.println("Key: " + key);
    return key;
  }

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    Category item = (Category) context.getExternalContext().getSessionMap().get(value);
    System.out.println("Value: " + item.getName());
    return item;
  }
}
