package de.fhb.view.forms;

import java.util.HashMap;
import java.util.Map;

// Convention: FormModels have to be called <EntityName>FormModel.java and extends abstract class FormModel
public class AuthorFormModel extends FormModel {

  public AuthorFormModel() {
    super.PROPERTY_ORDER = new String[]{"id", "name", "created", "lastModified"};
  }

  @Override
  public FormInput[] parseProperties(Map<String, Class<?>> properties) {
    HashMap<String, FormInput> inputs = new HashMap<>(properties.size());
    FormInput[] formFields = new FormInput[properties.size()];

    // Parse each property and collect them in a map
    for (Map.Entry<String, Class<?>> property : properties.entrySet()) {
      FormInput input = new FormInput(property);

      String key = input.getKey();

      if (key.equals("id") || key.equals("created") || key.equals("lastModified")) {
        input.setReadOnly(true);
      }

      switch (key) {
        case "id":
          input.setReadOnly(true);
          break;
        case "name":
          break;
        case "created":
          input.setReadOnly(true);
          break;
        case "lastModified":
          input.setReadOnly(true);
          break;
      }

      inputs.put(key, input);
    }

    // Sort properties according to "PROPERTY_ORDER"
    int i = 0;
    for (String orderedKey : PROPERTY_ORDER) {
      formFields[i] = inputs.get(orderedKey);
      i++;
    }

    return formFields;
  }
}
