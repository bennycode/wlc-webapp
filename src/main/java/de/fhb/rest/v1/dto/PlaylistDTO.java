package de.fhb.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fhb.entities.Difficulty;
import java.util.List;
import java.util.Objects;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "title", "language", "categoryName", "providerName", "numberOfVideos", "description", "owner", "status"})
public class PlaylistDTO extends BaseDTO {

  private Integer id;
  private String code;
  @JsonProperty("name")//new 
  private String title;
  private String slug;
  private String description;
  private List<VideoDTO> videoList;
  private LanguageDTO languageId;
  private ProviderDTO provider;
  private OwnerDTO owner;
  private CategoryDTO categoryId;
  private Difficulty difficulty;
  // TODO: Persist all transient variables
  @Transient
  private String categoryName;
  @Transient
  private String providerName;
  @Transient
  private int numberOfVideos;
//  @Transient new
  private String language;
  @Transient
  private StatusDTO status;

  public PlaylistDTO() {
    this.status = new StatusDTO();
  }

  public PlaylistDTO(Integer id) {
    this.id = id;
  }

  public PlaylistDTO(Integer id, String title, String slug) {
    this.id = id;
    this.title = title;
    this.slug = slug;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @XmlTransient
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @XmlElement(name = "name")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @XmlTransient
  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @XmlTransient
  public List<VideoDTO> getVideoList() {
    return videoList;
  }

  public void setVideoList(List<VideoDTO> videoList) {
    this.videoList = videoList;
  }

  // TODO: Rename "languageId" to "language" everywhere!
  @XmlTransient
  public LanguageDTO getLanguageId() {
    return languageId;
  }

  public void setLanguageId(LanguageDTO languageId) {
    this.languageId = languageId;
  }

  @XmlTransient
  public ProviderDTO getProvider() {
    return provider;
  }

  public void setProvider(ProviderDTO provider) {
    this.provider = provider;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public int getNumberOfVideos() {
    return numberOfVideos;
  }

  public void setNumberOfVideos(int numberOfVideos) {
    this.numberOfVideos = numberOfVideos;
  }

  public OwnerDTO getOwner() {
    return owner;
  }

  public void setOwner(OwnerDTO owner) {
    this.owner = owner;
  }

  @XmlTransient
  public CategoryDTO getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(CategoryDTO categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getLanguage() {
    return languageId.getName();
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public StatusDTO getStatus() {
    if (id == 47) {
      status.setIsErrorless(false);
    }
    if (id == 50) {
      status.setIsEmbeddable(false);
    }

    return status;
  }

  public void setStatus(StatusDTO status) {
    this.status = status;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 37 * hash + Objects.hashCode(this.id);
    hash = 37 * hash + Objects.hashCode(this.code);
    hash = 37 * hash + Objects.hashCode(this.title);
    hash = 37 * hash + Objects.hashCode(this.slug);
    hash = 37 * hash + Objects.hashCode(this.description);
    hash = 37 * hash + Objects.hashCode(this.videoList);
    hash = 37 * hash + Objects.hashCode(this.languageId);
    hash = 37 * hash + Objects.hashCode(this.provider);
    hash = 37 * hash + Objects.hashCode(this.owner);
    hash = 37 * hash + Objects.hashCode(this.categoryId);
    hash = 37 * hash + Objects.hashCode(this.categoryName);
    hash = 37 * hash + Objects.hashCode(this.providerName);
    hash = 37 * hash + this.numberOfVideos;
    hash = 37 * hash + Objects.hashCode(this.language);
    hash = 37 * hash + Objects.hashCode(this.status);
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
    final PlaylistDTO other = (PlaylistDTO) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "PlaylistDTO{" + "id=" + id + '}';
  }
}
