package com.welovecoding.tutorial.data.base;

import com.welovecoding.tutorial.view.scaffolding.ComponentFactory;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@EntityListeners(EntityListener.class)
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  protected String name;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date created;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date lastModified;

  public BaseEntity() {
    this.created = new Date();
    this.lastModified = new Date();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.id);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final BaseEntity other = (BaseEntity) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {
    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
  }

  public String propertyLog() {
    Class<?> aClass = this.getClass();
    Field[] declaredFields = aClass.getDeclaredFields();
    Map<String, String> logEntries = new HashMap<>();

    for (Field field : declaredFields) {
      field.setAccessible(true);

      Object[] arguments;
      try {
        arguments = new Object[]{
          field.getName(),
          field.getType().getSimpleName(),
          String.valueOf(field.get(this))
        };
      } catch (IllegalArgumentException | IllegalAccessException ex) {
        arguments = new Object[]{
          field.getName(),
          field.getType().getSimpleName(),
          ""
        };
      }

      String template = "- Property: {0} (\"{2}\", {1})";
      String logMessage = System.getProperty("line.separator")
              + MessageFormat.format(template, arguments);

      logEntries.put(field.getName(), logMessage);
    }

    SortedSet<String> sortedLog = new TreeSet<>(logEntries.keySet());

    StringBuilder sb = new StringBuilder("Class properties:");

    Iterator<String> it = sortedLog.iterator();
    while (it.hasNext()) {
      String key = it.next();
      sb.append(logEntries.get(key));
    }

    return sb.toString();
  }

}
