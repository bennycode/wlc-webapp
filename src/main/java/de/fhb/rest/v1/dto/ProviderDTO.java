package de.fhb.rest.v1.dto;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ProviderDTO extends BaseDTO {

  private Integer id;
  private String name;
  private List<PlaylistDTO> playlistList;

  public ProviderDTO() {
  }

  public ProviderDTO(Integer id) {
    this.id = id;
  }

  public ProviderDTO(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    hash = 23 * hash + Objects.hashCode(this.id);
    hash = 23 * hash + Objects.hashCode(this.name);
    hash = 23 * hash + Objects.hashCode(this.playlistList);
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
    final ProviderDTO other = (ProviderDTO) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "ProviderDTO{" + "id=" + id + '}';
  }
}
