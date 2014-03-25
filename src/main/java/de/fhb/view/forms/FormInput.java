package de.fhb.view.forms;

import java.util.Map;

public class FormInput {

  private int index;
  // TODO: Label is not used yet. AuthorForm should set it!
  private String label; // "Zuletzt bearbeitet"
  private String key; // "lastModified"
  private Class<?> value;
  private boolean readOnly;

  public FormInput() {
    this.readOnly = false;
    this.index = 0;
  }

  public FormInput(Map.Entry<String, Class<?>> property) {
    this.key = property.getKey();
    this.value = property.getValue();
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public boolean isReadOnly() {
    return this.readOnly;
  }

  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Class<?> getValue() {
    return value;
  }

  public void setValue(Class<?> value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "FormInput{" + "key=" + key + ", value=" + value + '}';
  }

}
