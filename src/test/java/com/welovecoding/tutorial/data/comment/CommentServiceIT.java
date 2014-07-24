package com.welovecoding.tutorial.data.comment;

import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.comment.entity.Comment;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import util.IntegrationTest;

/**
 *
 * @author Michael Koppen
 */
public class CommentServiceIT extends IntegrationTest {

  private static final Logger LOG = Logger.getLogger(CommentServiceIT.class.getName());

  @Rule
  // Get name of actual Test with test.getMethodName()
  public TestName test = new TestName();

  private CommentService cut;

  @BeforeClass
  public static void setUpClass() throws Exception {
    IntegrationTest.setUpClass();
    flatXmlDataSet = new FlatXmlDataSet(new File("src/test/resources", "full.xml"), false, true);
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
    IntegrationTest.tearDownClass();
  }

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    cut = IntegrationTest.lookupBy(CommentService.class);
  }

  @After
  @Override
  public void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test of getCategories method, of class CategoryResource.
   */
  @Test
  public void testCreateComment() throws Exception {
    System.out.println(test.getMethodName());
    Comment expected = cut.create(1l, Category.class, new Comment("title", "Text"));

    List<Comment> comments = cut.findAllAttached(1l, Category.class);

    Assert.assertFalse(comments.isEmpty());
    Assert.assertEquals(expected, comments.get(0));

    System.out.println("CommentID: " + comments.get(0).getId());
    System.out.println("CommentName: " + comments.get(0).getName());
    System.out.println("CommentSlug: " + comments.get(0).getSlug());
    System.out.println("CommentText: " + comments.get(0).getText());
    System.out.println("CommentCreated: " + comments.get(0).getCreated());
    System.out.println("CommentLastModified: " + comments.get(0).getLastModified());
    System.out.println("CommentCreator: " + comments.get(0).getCreator());
    System.out.println("CommentLastEditor: " + comments.get(0).getLastEditor());

  }

}
