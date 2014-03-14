package de.fhb.view.forms;

import java.util.Map;

// Convention: FormModels have to be called <EntityName>FormModel.java and extends abstract class FormModel
public class AuthorFormModel extends FormModel {

  // probably a List would be a better returnvalue.
  @Override
  public FormInput[] parseProperties(Map<String, Class<?>> properties) {
    FormInput[] formFields = new FormInput[4];

    for (Map.Entry<String, Class<?>> property : properties.entrySet()) {
      FormInput input = new FormInput(property);
      String key = input.getKey();

      if (key.equals("id") || key.equals("created") || key.equals("lastModified")) {
        input.setReadOnly(true);
      }

      switch (key) {
        case "id":
          formFields[0] = input;
          input.setReadOnly(true);
          break;
        case "name":
          formFields[1] = input;
          break;
        case "created":
          formFields[2] = input;
          input.setReadOnly(true);
          break;
        case "lastModified":
          formFields[3] = input;
          input.setReadOnly(true);
          break;
      }

    }

    return formFields;
  }
}
