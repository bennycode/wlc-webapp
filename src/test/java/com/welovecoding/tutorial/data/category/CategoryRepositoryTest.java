package com.welovecoding.tutorial.data.category;

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
public class CategoryRepositoryTest extends BaseRepositoryTest<CategoryRepository, Category> {

  @Spy
  private Logger logger = Logger.getLogger(CategoryRepository.class.getName());

  public CategoryRepositoryTest() {
    super(new CategoryRepository(), Category.class);
  }

}
