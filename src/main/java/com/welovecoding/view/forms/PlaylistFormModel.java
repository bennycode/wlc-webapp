package com.welovecoding.view.forms;

import java.util.HashMap;
import java.util.Map;

public class PlaylistFormModel extends FormModel {

  public PlaylistFormModel() {
    super.PROPERTY_ORDER = new String[]{
      "id",
      "name",
      "category",
      "difficulty",
      "description",
      "languageCode",
      "provider",
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

      // Set read-only
      if (key.equals("id") || key.equals("created") || key.equals("lastModified")) {
        input.setReadOnly(true);
      }

      // Set render-type
      switch (key) {
        case "category":
          input.setRenderType(RenderType.DROPDOWN);
          break;
        case "description":
          input.setRenderType(RenderType.TEXTAREA);
          break;
        case "difficulty":
          input.setRenderType(RenderType.ENUM);
          break;
        case "languageCode":
          input.setRenderType(RenderType.ENUM);
          break;
        case "provider":
          input.setRenderType(RenderType.ENUM);
          break;
        default:
          setDefaultRenderType(input);
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
