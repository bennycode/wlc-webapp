package de.fhb.view.forms;

import java.util.Map;

public abstract class FormModel {

  protected String[] PROPERTY_ORDER;

  public abstract FormInput[] parseProperties(Map<String, Class<?>> properties);
}
