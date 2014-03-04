package de.fhb.entities;

import java.io.Serializable;

/**
 *
 * @author Benny Neugebauer
 */
// TODO This is an old entity which maybe have to be mapped to the new structure
public class Status implements Serializable {

	// If a CERTAIN AMOUT of users send error reports related to a video or playlist then isErrorless is false.
  // Note: 1st & 3rd video of Playlist ID 47 (HTML5 & CSS3 Fundamentals: Development for Absolute Beginners) are faulty.
  private boolean isErrorless = true;
	// If an owner blocks embedding via YouTube then isEmbeddable is false.
  // Note: Embedding for Playlist ID 50 (Android Apps programmieren) is not allowed.
  // Workaround: With direct access to the MP4 url it can be played.
  private boolean isEmbeddable = true;
  // If a device (e.g. Amazon Kindle) does not support a video codec then isPlayable is false.
  private boolean isPlayable = true;

  public Status() {
  }

  public boolean isErrorless() {
    return isErrorless;
  }

  public void setIsErrorless(boolean isErrorless) {
    this.isErrorless = isErrorless;
  }

  public boolean isEmbeddable() {
    return isEmbeddable;
  }

  public void setIsEmbeddable(boolean isEmbeddable) {
    this.isEmbeddable = isEmbeddable;
  }

  public boolean isPlayable() {
    return isPlayable;
  }

  public void setIsPlayable(boolean isPlayable) {
    this.isPlayable = isPlayable;
  }
}
