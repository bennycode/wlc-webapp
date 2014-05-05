package com.welovecoding.tutorial.view.scaffolding;

import java.util.Map;

public abstract class FormModel {

  protected String[] PROPERTY_ORDER;

  public abstract FormInput[] parseProperties(Map<String, Class<?>> properties);

  public void setDefaultRenderType(FormInput property) {
    String className = property.getValue().getName();

    switch (className) {
      case "java.util.Date":
        property.setRenderType(RenderType.DATETIME);
        break;
      case "java.util.List":
        property.setRenderType(RenderType.LIST);
        break;
      default:
        property.setRenderType(RenderType.TEXT);
    }
  }
}
