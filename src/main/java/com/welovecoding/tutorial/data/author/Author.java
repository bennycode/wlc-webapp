package com.welovecoding.tutorial.data.author;

import static com.welovecoding.tutorial.data.author.Author.ORDER_BY_NAME;
import com.welovecoding.tutorial.data.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
  @NamedQuery(name = ORDER_BY_NAME, query = "SELECT a FROM Author a ORDER BY a.name")
})
public class Author extends BaseEntity {

  public static final String ORDER_BY_NAME = "Author.orderByName";

  @Size(min = 0, max = 1024)
  @Column(length = 1024)
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
