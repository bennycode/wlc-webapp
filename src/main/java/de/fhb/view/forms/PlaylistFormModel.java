package de.fhb.view.forms;

import java.util.HashMap;
import java.util.Map;

public class PlaylistFormModel extends FormModel {

  public PlaylistFormModel() {
    super.PROPERTY_ORDER = new String[]{
      "id",
      "name",
      "category",
      "description",
      "languageCode",
      "providerName",
      "code",
      "videos",
      "created",
      "lastModified"
    };
  }

  @Override
  public FormInput[] parseProperties(Map<String, Class<?>> properties) {
    int size = PROPERTY_ORDER.length;
    HashMap<String, FormInput> inputs = new HashMap<>(size);
    FormInput[] formFields = new FormInput[size];

    // Parse each property and collect them in a map
    for (Map.Entry<String, Class<?>> property : properties.entrySet()) {
      FormInput input = new FormInput(property);

      String key = input.getKey();

      // Readonly
      if (key.equals("id") || key.equals("created") || key.equals("lastModified")) {
        input.setReadOnly(true);
      }

      if (key.equals("category")) {
        input.setRenderType(RenderType.DROPDOWN);
      } else {
        setDefaultRenderType(input);
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
