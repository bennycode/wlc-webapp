package com.welovecoding.tutorial.data.video;

import com.welovecoding.tutorial.data.base.BaseEntity;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import static com.welovecoding.tutorial.data.video.Video.FIND_ALL_IN_CATEGORY;
import static com.welovecoding.tutorial.data.video.Video.FIND_ALL_IN_CATEGORY_AND_PLAYLIST;
import static com.welovecoding.tutorial.data.video.Video.FIND_ALL_IN_PLAYLIST;
import static com.welovecoding.tutorial.data.video.Video.FIND_BY_CODE;
import static com.welovecoding.tutorial.data.video.Video.FIND_BY_PLAYLIST_AND_SLUG;
import static com.welovecoding.tutorial.data.video.Video.FIND_IN_CATEGORY;
import static com.welovecoding.tutorial.data.video.Video.FIND_IN_CATEGORY_AND_PLAYLIST;
import static com.welovecoding.tutorial.data.video.Video.FIND_IN_PLAYLIST;
import static com.welovecoding.tutorial.data.video.Video.LIKE_NAME;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
  @NamedQuery(name = FIND_BY_CODE, query = "SELECT v FROM Video v WHERE v.code = :code"),
  @NamedQuery(name = LIKE_NAME, query = "SELECT v FROM Video v WHERE UPPER(v.name) LIKE UPPER(:keyword)"),
  @NamedQuery(name = FIND_ALL_IN_CATEGORY, query = "SELECT v FROM Video v WHERE v.playlist.category.id = :playlistid"),
  @NamedQuery(name = FIND_IN_PLAYLIST, query = "SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.id = :playlistid"),
  @NamedQuery(name = FIND_ALL_IN_PLAYLIST, query = "SELECT v FROM Video v WHERE v.playlist.id = :playlistid"),
  @NamedQuery(name = FIND_ALL_IN_CATEGORY_AND_PLAYLIST, query = "SELECT v FROM Video v WHERE v.playlist.category.id = :categoryid AND v.playlist.id = :playlistid"),
  @NamedQuery(name = FIND_IN_CATEGORY, query = "SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.category.id = :categoryid"),
  @NamedQuery(name = FIND_IN_CATEGORY_AND_PLAYLIST, query = "SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.category.id = :categoryid AND v.playlist.id = :playlistid"),
  @NamedQuery(name = FIND_BY_PLAYLIST_AND_SLUG, query = "SELECT v FROM Video v WHERE v.slug = :videoslug AND v.playlist.id = :playlistid")
})
public class Video extends BaseEntity {

  public static final String FIND_BY_CODE = "Video.findByCode";
  public static final String LIKE_NAME = "Video.likeName";
  public static final String FIND_ALL_IN_CATEGORY = "Video.findAllInCategory";
  public static final String FIND_IN_PLAYLIST = "Video.findInPlaylist";
  public static final String FIND_ALL_IN_PLAYLIST = "Video.findAllInPlaylist";
  public static final String FIND_ALL_IN_CATEGORY_AND_PLAYLIST = "Video.findAllInCategoryAndPlaylist";
  public static final String FIND_IN_CATEGORY = "Video.findInCategory";
  public static final String FIND_IN_CATEGORY_AND_PLAYLIST = "Video.findInCategoryAndPlaylist";
  public static final String FIND_BY_PLAYLIST_AND_SLUG = "Video.findByPlaylistAndSlug";

  @Column(unique = true)
  private String code;

  @Size(min = 0, max = 1024)
  @Column(length = 1024)
  private String description;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @NotNull
  private Playlist playlist;

  private String previewImageUrl;

  private String downloadUrl;

  private String permalink;

  public Video() {
  }

  public Video(String code, String name, String description) {
    this.code = code;
    this.setName(name);
    this.setDescription(description);
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
    if (description != null && description.length() > 1024) {
      description = description.substring(0, 1024);
    }

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
    this.permalink = permalink;
  }

  @Override
  public String toString() {
    return this.getName();
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.code);
    hash = 97 * hash + Objects.hashCode(this.getId());
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
    final Video other = (Video) obj;
    if (!Objects.equals(this.code, other.code)) {
      return false;
    }
    if (!super.equals(other)) {
      return false;
    }
    return true;
  }

}
