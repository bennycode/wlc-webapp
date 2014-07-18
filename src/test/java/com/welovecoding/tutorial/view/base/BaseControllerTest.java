package com.welovecoding.tutorial.view.base;

import com.welovecoding.tutorial.view.base.BaseController;
import com.welovecoding.tutorial.data.author.AuthorService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BaseControllerTest {

  private BaseController instance;

  public BaseControllerTest() {
  }

  @Before
  public void setUp() {
    instance = new BaseControllerImpl();
  }

  @Test
  public void testTotalPages() {
    instance.setItemsPerPage(0);

    int expected = 1;
    int actual = instance.getTotalPages();

    assertEquals(expected, actual);
  }

  public class BaseControllerImpl<BaseEntity, BaseService> extends BaseController {

    @Override
    public AuthorService getService() {
      return (AuthorService) null;
    }

    @Override
    public int getItemSize() {
      return 0;
    }
  }
}
