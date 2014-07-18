package com.welovecoding.tutorial.data.user;

import com.welovecoding.tutorial.data.base.BaseRepositoryTest;
import com.welovecoding.tutorial.data.user.entity.User;
import java.util.logging.Logger;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Koppen
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest extends BaseRepositoryTest<UserRepository, User> {

  @Spy
  private Logger logger = Logger.getLogger(UserRepository.class.getName());

  public UserRepositoryTest() {
    super(new UserRepository(), User.class);
  }

}
