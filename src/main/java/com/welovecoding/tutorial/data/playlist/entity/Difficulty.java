package com.welovecoding.tutorial.data.playlist.entity;

public enum Difficulty implements IEnumLabel {

  EASY("admin.form.label.beginner"),
  MEDIUM("admin.form.label.advanced"),
  HARD("admin.form.label.pros");

  private final String label;

  private Difficulty(String label) {
    this.label = label;
  }

  @Override
  public String getLabel() {
    return label;
  }

}
