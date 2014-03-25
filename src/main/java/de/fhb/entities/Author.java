package de.fhb.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
  @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
  @NamedQuery(name = "Author.getCount", query = "SELECT COUNT(a) FROM Author a")
})
public class Author extends BaseEntity {

  public static final String FIND_ALL = "Author.findAll";
  public static final String GET_COUNT = "Author.getCount";

  @Size(min = 0, max = 255)
  private String description;
  @Size(min = 0, max = 255)
  private String website;
  @Size(min = 0, max = 255)
  private String channelUrl;

  public Author() {
    this.description = "";
    this.website = "";
    this.channelUrl = "";
  }

  public Author(String name) {
    this();
    this.setName(name);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getChannelUrl() {
    return channelUrl;
  }

  public void setChannelUrl(String channelUrl) {
    this.channelUrl = channelUrl;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
