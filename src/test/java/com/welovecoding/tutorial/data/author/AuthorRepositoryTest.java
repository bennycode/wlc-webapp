package com.welovecoding.tutorial.data.author;

import com.welovecoding.tutorial.data.base.BaseRepositoryTest;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Koppen
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorRepositoryTest extends BaseRepositoryTest<AuthorRepository, Author> {

  @Spy
  private Logger logger = Logger.getLogger(AuthorRepository.class.getName());

  public AuthorRepositoryTest() {
    super(new AuthorRepository(), Author.class);
  }

  @Test
  public void testOrderByName() {
    List<Author> expected = Arrays.asList(new Author());

    // simulate behaviour
    when(em.createNamedQuery(anyString(), eq(Author.class))).thenReturn(tQuery);
    when(tQuery.getResultList()).thenReturn(expected);

    // act
    List<Author> result = cut.findAllOrderedByName();
    Assert.assertEquals(result, expected);

    // verify all methods are called
    verify(em).createNamedQuery(anyString(), eq(Author.class));
    verify(tQuery).getResultList();
  }

  /**
   * On fail findAllOrderedByName() should return an empty list.
   */
  @Test
  public void testOrderByNameFail() {
    // simulate behaviour
    when(em.createNamedQuery(anyString(), eq(Author.class))).thenReturn(tQuery);
    when(tQuery.getResultList()).thenThrow(
            new IllegalStateException(),
            new QueryTimeoutException(),
            new TransactionRequiredException(),
            new PessimisticLockException(),
            new LockTimeoutException(),
            new PersistenceException());

    // act
    // throws IllegalStateException
    List<Author> result = cut.findAllOrderedByName();
    Assert.assertThat(result, Matchers.emptyCollectionOf(Author.class));

    // throws QueryTimeoutException
    result = cut.findAllOrderedByName();
    Assert.assertThat(result, Matchers.emptyCollectionOf(Author.class));

    // throws TransactionRequiredException
    result = cut.findAllOrderedByName();
    Assert.assertThat(result, Matchers.emptyCollectionOf(Author.class));

    // throws PessimisticLockException
    result = cut.findAllOrderedByName();
    Assert.assertThat(result, Matchers.emptyCollectionOf(Author.class));

    // throws LockTimeoutException
    result = cut.findAllOrderedByName();
    Assert.assertThat(result, Matchers.emptyCollectionOf(Author.class));

    // throws PersistenceException
    result = cut.findAllOrderedByName();
    Assert.assertThat(result, Matchers.emptyCollectionOf(Author.class));

    // verify all methods are called 
    verify(em, times(6)).createNamedQuery(anyString(), eq(Author.class));
    verify(tQuery, times(6)).getResultList();
  }
}
