package com.welovecoding.tutorial.view.video;

import com.welovecoding.tutorial.view.scaffolding.FormInput;
import com.welovecoding.tutorial.view.scaffolding.FormModel;
import com.welovecoding.tutorial.view.scaffolding.RenderType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Convention: FormModels have to be called <EntityName>FormModel.java and extends abstract class FormModel
public class VideoFormModel extends FormModel {

  public VideoFormModel() {
    super.PROPERTY_ORDER = new String[]{
      "id",
      "name",
      "description",
      "playlist",
      "code",
      "previewImageUrl",
      "downloadUrl",
      "permalink",
      "created",
      "lastModified"
    };
  }

  @Override
  public FormInput[] parseProperties(Map<String, Class<?>> properties) {
    HashMap<String, FormInput> inputs = new HashMap<>(properties.size());
    List<FormInput> formFields = new ArrayList<>();

    // Parse each property and collect them in a map
    for (Map.Entry<String, Class<?>> property : properties.entrySet()) {
      FormInput input = new FormInput(property);
      setDefaultRenderType(input);

      String key = input.getKey();

      switch (key) {
        case "id":
        case "created":
        case "lastModified":
          input.setReadOnly(true);
          break;
        case "playlist":
          input.setRenderType(RenderType.DROPDOWN);
          break;
        case "description":
          input.setRenderType(RenderType.TEXTAREA);
          break;
      }

      inputs.put(key, input);
    }

    // Sort and filter properties according to "PROPERTY_ORDER"
    for (String orderedKey : PROPERTY_ORDER) {
      FormInput field = inputs.get(orderedKey);
      if (field != null) {
        formFields.add(field);
      } else {
        System.out.println("WARNING: Couldn't find a field with the key " + orderedKey);
      }
    }

    return formFields.toArray(new FormInput[]{});
  }
}
