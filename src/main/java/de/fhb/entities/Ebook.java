package de.fhb.entities;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Benny
 */
// TODO This is an old entity which maybe have to be mapped to the new structure
@Entity
@Cacheable
@NamedQueries({
  @NamedQuery(name = "Ebook.findByTitle", query = "SELECT e FROM Ebook e WHERE e.title = :title"),
  @NamedQuery(name = "Ebook.findByDownloadUrl", query = "SELECT e FROM Ebook e WHERE e.downloadUrl = :downloadUrl"),
  @NamedQuery(name = "Ebook.findByFree", query = "SELECT e FROM Ebook e WHERE e.free = :free"),
  @NamedQuery(name = "Ebook.findByKindle", query = "SELECT e FROM Ebook e WHERE e.kindle = :kindle")})
public class Ebook extends BaseEntity {

  @Column(nullable = false, length = 255, unique = true)
  private String title;
  @Column(name = "DOWNLOAD_URL", nullable = false, length = 255)
  private String downloadUrl;
  @Column(nullable = false)
  private boolean free;
  @Column(nullable = false)
  private boolean kindle;
  @ManyToOne
  @JoinColumn(name = "LANGUAGEENTRY_ID", referencedColumnName = "ID")
  private LanguageEntry language;

  public Ebook() {
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

  public LanguageEntry getLanguage() {
    return language;
  }

  public void setLanguage(LanguageEntry language) {
    this.language = language;
  }

}
