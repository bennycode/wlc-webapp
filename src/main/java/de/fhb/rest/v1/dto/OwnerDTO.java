package de.fhb.rest.v1.dto;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "name", "website", "description"})
public class OwnerDTO extends BaseDTO {

  private Integer id;
  private Integer type;
  private String name;
  private String description;
  private String website;
  private String channelUrl;
  private List<PlaylistDTO> playlistList;

  public OwnerDTO() {
  }

  public OwnerDTO(Integer id) {
    this.id = id;
  }

  public OwnerDTO(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  @XmlTransient
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @XmlTransient
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  @XmlTransient
  public String getChannelUrl() {
    return channelUrl;
  }

  public void setChannelUrl(String channelUrl) {
    this.channelUrl = channelUrl;
  }

  @XmlTransient
  public List<PlaylistDTO> getPlaylistList() {
    return playlistList;
  }

  public void setPlaylistList(List<PlaylistDTO> playlistList) {
    this.playlistList = playlistList;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 53 * hash + Objects.hashCode(this.id);
    hash = 53 * hash + Objects.hashCode(this.type);
    hash = 53 * hash + Objects.hashCode(this.name);
    hash = 53 * hash + Objects.hashCode(this.description);
    hash = 53 * hash + Objects.hashCode(this.website);
    hash = 53 * hash + Objects.hashCode(this.channelUrl);
    hash = 53 * hash + Objects.hashCode(this.playlistList);
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
    final OwnerDTO other = (OwnerDTO) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "OwnerDTO{" + "id=" + id + '}';
  }
}
