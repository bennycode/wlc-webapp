package com.welovecoding.tutorial;

import com.welovecoding.StringUtils;
import com.welovecoding.tutorial.data.category.Category;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {

  public StringUtilsTest() {
  }

  public void testCapitalize() {
    String expected = "Category";
    String actual = StringUtils.capitalize("category");
    assertEquals(expected, actual);
  }

  @Test
  public void testLowerFirstChar() {
    String expected = "category";
    String actual = StringUtils.lowerFirstChar("Category");
    assertEquals(expected, actual);
  }

  @Test
  public void testGetControllerBeanName_Object() {
    String expected = "categoryController";
    String actual = StringUtils.getControllerBeanName(new Category());
    assertEquals(expected, actual);
  }

  @Test
  public void testGetControllerBeanName_String() {
    String expected = "categoryController";
    String actual = StringUtils.getControllerBeanName("Category");
    assertEquals(expected, actual);
  }

  @Test
  public void testGetControllerBeanNameByStringValue() {
    String expected = "categoryController";
    String actual = StringUtils.getControllerBeanNameByStringValue(new Category().toString());
    assertEquals(expected, actual);
  }

  @Test
  public void testGetIdByStringValue() {
    Category category = new Category();
    category.setId(5L);

    Long expected = 5L;
    Long actual = StringUtils.getIdByStringValue(category.toString());
    assertEquals(expected, actual);
  }

}
