package com.welovecoding.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
  @NamedQuery(name = "Video.findByCode", query = "SELECT v FROM Video v WHERE v.code = :code"),
  @NamedQuery(name = "Video.likeName", query = "SELECT v FROM Video v WHERE UPPER(v.name) LIKE UPPER(:keyword)"),
  @NamedQuery(name = "Video.findAllInPlaylist", query = "SELECT v FROM Video v WHERE v.playlist.id = :playlistid"),
  @NamedQuery(name = "Video.findAllInCategory", query = "SELECT v FROM Video v WHERE v.playlist.category.id = :playlistid"),
  @NamedQuery(name = "Video.findInPlaylist", query = "SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.id = :playlistid"),
  @NamedQuery(name = "Video.findInCategory", query = "SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.category.id = :playlistid"),
  @NamedQuery(name = "Video.findInCategoryAndPlaylist", query = "SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.category.id = :categoryid AND v.playlist.id = :playlistid")
})
public class Video extends BaseEntity {

  public static final String FIND_BY_CODE = "Video.findByCode";
  public static final String LIKE_NAME = "Video.likeName";
  public static final String FIND_ALL_IN_PLAYLIST = "Video.findAllInPlaylist";
  public static final String FIND_ALL_IN_CATEGORY = "Video.findAllInCategory";
  public static final String FIND_IN_PLAYLIST = "Video.findInPlaylist";
  public static final String FIND_IN_CATEGORY = "Video.findInCategory";
  public static final String FIND_IN_CATEGORY_AND_PLAYLIST = "Video.findInCategoryAndPlaylist";

  @Column(unique = true)
  private String code;

  @Size(min = 0, max = 1024)
  @Column(length = 1024)
  private String description;

  @NotNull
  @ManyToOne(cascade = CascadeType.PERSIST)
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
