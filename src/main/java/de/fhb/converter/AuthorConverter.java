package de.fhb.converter;

import de.fhb.entities.Author;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("AuthorConverter")
public class AuthorConverter implements Converter {

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    Author item = (Author) value;
    String key = AuthorConverter.class.getName() + item.getId();
    context.getExternalContext().getSessionMap().put(key, item);
    System.out.println("Key: " + key);
    return key;
  }

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    Author item = (Author) context.getExternalContext().getSessionMap().get(value);
    System.out.println("Value: " + item.getName());
    return item;
  }
}
