package de.fhb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Video.findByCode", query = "SELECT v FROM Video v WHERE v.code = :code"),
  @NamedQuery(name = "Video.likeName", query = "SELECT v FROM Video v WHERE UPPER(v.name) LIKE UPPER(:keyword)")
})
public class Video extends BaseEntity {

  public static final String FIND_BY_CODE = "Video.findByCode";
  public static final String LIKE_NAME = "Video.likeName";

  @Column(unique = true)
  private String code;
  private String description;

  public Video() {
  }

  public Video(String code, String name, String description) {
    this.code = code;
    super.setName(name);
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return super.toString();
  }

}
