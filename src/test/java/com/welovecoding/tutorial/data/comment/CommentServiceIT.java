package com.welovecoding.tutorial.data.comment;

import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.comment.entity.Comment;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

//  @BeforeClass
//  public static void setUpClass() throws Exception {
//    LOG.info("[CommentServiceIT] calling setUpClass()");
//    IntegrationTest.setUpClass();
//    flatXmlDataSet = new FlatXmlDataSet(new File("src/test/resources", "full.xml"), false, true);
//  }
//
//  @AfterClass
//  public static void tearDownClass() throws Exception {
//    LOG.info("[CommentServiceIT] calling tearDownClass()");
//    IntegrationTest.setUpClass();
//  }
  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    cut = (CommentService) new InitialContext().lookup("java:global/WeLoveCodingTest/CommentService");
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
    Comment expected = cut.createComment(1l, Category.class, new Comment("title", "Text"));

    List<Comment> comments = cut.getAllCommentsOfCommentBag(1l, Category.class);

    Assert.assertFalse(comments.isEmpty());
    Assert.assertEquals(expected, comments.get(0));
  }

}
