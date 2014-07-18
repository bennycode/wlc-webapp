package com.welovecoding.tutorial.data.playlist;

import com.welovecoding.tutorial.data.base.BaseRepositoryTest;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import java.util.logging.Logger;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Koppen
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaylistRepositoryTest extends BaseRepositoryTest<PlaylistRepository, Playlist> {

  @Spy
  private Logger logger = Logger.getLogger(PlaylistRepository.class.getName());

  public PlaylistRepositoryTest() {
    super(new PlaylistRepository(), Playlist.class);
  }

}
