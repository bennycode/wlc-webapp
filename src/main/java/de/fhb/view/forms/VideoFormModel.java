package de.fhb.view.forms;

import java.util.HashMap;
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
    int size = PROPERTY_ORDER.length;
    HashMap<String, FormInput> inputs = new HashMap<>(size);
    FormInput[] formFields = new FormInput[size];

    // Parse each property and collect them in a map
    for (Map.Entry<String, Class<?>> property : properties.entrySet()) {
      FormInput input = new FormInput(property);
      setDefaultRenderType(input);
      
      String key = input.getKey();
      
      if (key.equals("id") || key.equals("created") || key.equals("lastModified")) {
        input.setReadOnly(true);
      }
      
      if (key.equals("playlist")) {
        input.setRenderType(RenderType.DROPDOWN);
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
