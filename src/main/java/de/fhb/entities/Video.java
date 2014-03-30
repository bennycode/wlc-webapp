package de.fhb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

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

  @NotNull
  @ManyToOne
  private Playlist playlist;

  private String previewImageUrl;

  private String downloadUrl;

  private String permalink;

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

  public Playlist getPlaylist() {
    return playlist;
  }

  public void setPlaylist(Playlist playlist) {
    this.playlist = playlist;
  }

  public String getPreviewImageUrl() {
    return previewImageUrl;
  }

  public void setPreviewImageUrl(String previewImageUrl) {
    this.previewImageUrl = previewImageUrl;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(String permalink) {
    /*
     String categorySlug = Slugify.slugify(video.getPlaylist().getCategory().getName());
     String playlistSlug = Slugify.slugify(video.getPlaylist().getName());
     String permalink = String.format("http://www.welovecoding.com/tutorials/%s/%s?video=0", categorySlug, playlistSlug);
     */
    this.permalink = permalink;
  }

  @Override
  public String toString() {
    return this.getName();
  }

}
