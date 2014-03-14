/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.view.forms;

import java.util.Map;

/**
 *
 * @author MacYser
 */
public abstract class FormModel {

  public abstract FormInput[] parseProperties(Map<String, Class<?>> properties);
}
