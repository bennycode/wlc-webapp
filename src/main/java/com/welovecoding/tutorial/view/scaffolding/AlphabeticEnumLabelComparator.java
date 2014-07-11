package com.welovecoding.tutorial.view.scaffolding;

import com.welovecoding.tutorial.data.playlist.entity.EnumLabel;
import java.util.Comparator;

public class AlphabeticEnumLabelComparator<T extends Enum & EnumLabel> implements Comparator<T> {

  @Override
  public int compare(T enum1, T enum2) {
    // TODO: Sadly the labels are just "admin.locale.en" so we have to resolve them
    // in order to do a proper comparision
    return enum1.getLabel().compareToIgnoreCase(enum2.getLabel());
  }

}
