package com.welovecoding.tutorial.data.video;

import com.welovecoding.tutorial.data.base.BaseRepositoryTest;
import java.util.logging.Logger;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Koppen
 */
@RunWith(MockitoJUnitRunner.class)
public class VideoRepositoryTest extends BaseRepositoryTest<VideoRepository, Video> {

  @Spy
  private Logger logger = Logger.getLogger(VideoRepository.class.getName());

  public VideoRepositoryTest() {
    super(new VideoRepository(), Video.class);
  }

}
