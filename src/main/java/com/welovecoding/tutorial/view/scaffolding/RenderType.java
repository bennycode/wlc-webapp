package com.welovecoding.tutorial.view.scaffolding;

/**
 * Render types for HTML elements which will be dynamically generated.
 *
 * @author Benny Neugebauer
 * @see com.welovecoding.tutorial.view.scaffolding.ComponentFactory
 */
public enum RenderType {

  /**
   * Render a "java.util.Date".
   */
  DATETIME,
  /**
   * Render values from the database (which have a controller) in a dropdown.
   */
  DROPDOWN,
  /**
   * Render static values (usually from "java.util.List") in a dropdown.
   */
  LIST,
  /**
   * Render values in an input field of type "text".
   */
  TEXT,
  /**
   * Render values in a "textarea" element.
   */
  TEXTAREA,
  /**
   * Render enums in a dropdown.
   */
  ENUM
}
