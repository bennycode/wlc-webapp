package de.fhb.view.jsf;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class CustomTag extends UIOutput {

  private final String name;

  public CustomTag(String name) {
    this.name = name;
  }

  @Override
  public void encodeBegin(FacesContext context) throws IOException {
    ResponseWriter writer = context.getResponseWriter();
    writer.startElement(this.name, this);

    if (this.getChildCount() > 0) {
      for (int i = 0; i < this.getChildCount(); i++) {
        UIComponent child = this.getChildren().get(i);
        child.encodeAll(context);
      }
    }
  }

  @Override
  public void encodeEnd(FacesContext context) throws IOException {
    ResponseWriter writer = context.getResponseWriter();
    writer.endElement(this.name);
  }
}
