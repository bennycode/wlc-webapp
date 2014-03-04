package de.fhb.rest.v1.dto;

import java.util.Objects;

public class EbookDTO extends BaseDTO {

  private Integer id;
  private String title;
  private String downloadUrl;
  private boolean free;
  private boolean kindle;
  private LanguageDTO languageId;

  public EbookDTO() {
  }

  public EbookDTO(Integer id) {
    this.id = id;
  }

  public EbookDTO(Integer id, String title, String downloadUrl, boolean free, boolean kindle) {
    this.id = id;
    this.title = title;
    this.downloadUrl = downloadUrl;
    this.free = free;
    this.kindle = kindle;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  public boolean isFree() {
    return free;
  }

  public void setFree(boolean free) {
    this.free = free;
  }

  public boolean isKindle() {
    return kindle;
  }

  public void setKindle(boolean kindle) {
    this.kindle = kindle;
  }

  public LanguageDTO getLanguageId() {
    return languageId;
  }

  public void setLanguageId(LanguageDTO languageId) {
    this.languageId = languageId;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.id);
    hash = 97 * hash + Objects.hashCode(this.title);
    hash = 97 * hash + Objects.hashCode(this.downloadUrl);
    hash = 97 * hash + (this.free ? 1 : 0);
    hash = 97 * hash + (this.kindle ? 1 : 0);
    hash = 97 * hash + Objects.hashCode(this.languageId);
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
    final EbookDTO other = (EbookDTO) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "EbookDTO{" + "id=" + id + '}';
  }
}
