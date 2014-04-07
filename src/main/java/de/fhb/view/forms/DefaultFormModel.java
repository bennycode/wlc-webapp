package de.fhb.view.forms;

import java.util.Map;

public class DefaultFormModel extends FormModel {

  @Override
  public FormInput[] parseProperties(Map<String, Class<?>> properties) {
    FormInput[] formFields = new FormInput[properties.size()];

    int i = 0;
    for (Map.Entry<String, Class<?>> property : properties.entrySet()) {
      FormInput input = new FormInput(property);
      setDefaultRenderType(input);
      formFields[i] = input;
      i++;
    }

    return formFields;
  }
}
