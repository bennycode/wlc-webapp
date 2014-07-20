package com.welovecoding.tutorial.data.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michael Koppen
 * @param <E>
 * @param <T>
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseRepositoryTest<T extends BaseRepository<E>, E extends Serializable> {

  @Spy
  private Logger logger = Logger.getLogger(BaseRepository.class.getName());

  @InjectMocks
  protected final T cut;

  @Mock
  protected EntityManager em;
  @Mock
  protected TypedQuery<E> tQuery;

  private Class<E> entityClass;

  public BaseRepositoryTest(T cutRepo, Class<E> entityClass) {
    this.entityClass = entityClass;
    this.cut = cutRepo;
  }

  @Test
  public void testCreate() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    // simulate behaviour
    doNothing().when(em).persist(anyObject());
    doNothing().when(em).flush();
    doReturn(expected).when(em).merge(anyObject());

    // act
    E result = cut.create(expected);
    Assert.assertEquals(result, expected);

    // verify all methods are called
    verify(em).persist(anyObject());
    verify(em).flush();
    verify(em).merge(anyObject());
  }

  @Test
  public void testCreateFail() {
    E expected = null;

    // simulate behaviour
    doThrow(new EntityExistsException()).
            doThrow(new IllegalArgumentException()).
            doThrow(new TransactionRequiredException()).
            doNothing().
            doNothing().
            doNothing().
            doNothing().
            when(em).persist(anyObject());
    doThrow(new PersistenceException()).
            doThrow(new TransactionRequiredException()).
            doNothing().
            doNothing().
            when(em).flush();
    doThrow(new TransactionRequiredException()).
            doThrow(new IllegalArgumentException()).
            when(em).merge(anyObject());

    // act
    // throws EntityExistsException
    E result = cut.create(expected);
    Assert.assertEquals(result, expected);

    // throws IllegalArgumentException
    result = cut.create(expected);
    Assert.assertEquals(result, expected);

    // throws TransactionRequiredException
    result = cut.create(expected);
    Assert.assertEquals(result, expected);

    // throws PersistenceException
    result = cut.create(expected);
    Assert.assertEquals(result, expected);

    // throws TransactionRequiredException
    result = cut.create(expected);
    Assert.assertEquals(result, expected);

    // throws TransactionRequiredException
    result = cut.create(expected);
    Assert.assertEquals(result, expected);

    // throws IllegalArgumentException
    result = cut.create(expected);
    Assert.assertEquals(result, expected);

    // verify all methods are called
    verify(em, times(7)).persist(anyObject());
    verify(em, times(4)).flush();
    verify(em, times(2)).merge(anyObject());
  }

  @Test
  public void testEdit() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    // simulate behaviour
    doReturn(expected).when(em).merge(anyObject());

    // act
    E result = cut.edit(expected);
    Assert.assertEquals(result, expected);

    // verify all methods are called
    verify(em).merge(anyObject());
  }

  @Test
  public void testEditFail() {
    E expected = null;

    // simulate behaviour
    doThrow(new TransactionRequiredException()).
            doThrow(new IllegalArgumentException()).
            when(em).merge(anyObject());

    // act
    // throws TransactionRequiredException
    E result = cut.edit(expected);
    Assert.assertEquals(result, expected);

    // throws IllegalArgumentException
    result = cut.edit(expected);
    Assert.assertEquals(result, expected);

    // verify all methods are called
    verify(em, times(2)).merge(anyObject());
  }

  @Test
  public void testRemove() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    // simulate behaviour
    doNothing().when(em).remove(anyObject());

    // act
    cut.remove(expected);

    // verify all methods are called
    verify(em).remove(anyObject());
  }

  @Test
  public void testRemoveFail() {
    E expected = null;

    // simulate behaviour
    doThrow(new TransactionRequiredException()).
            doThrow(new IllegalArgumentException()).
            when(em).remove(anyObject());

    // act
    // throws TransactionRequiredException
    cut.remove(expected);

    // throws IllegalArgumentException
    cut.remove(expected);

    // verify all methods are called
    verify(em, times(2)).remove(anyObject());
  }

  @Test
  public void testBatchCreate() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    List<E> expectedList = new ArrayList<>();
    expectedList.add(expected);
    // simulate behaviour
    doNothing().when(em).persist(anyObject());
    doNothing().when(em).flush();

    // act
    cut.batchCreate(expectedList.iterator());

    // verify all methods are called
    verify(em).persist(anyObject());
    verify(em).flush();
  }

  @Test
  public void testBatchCreateFail() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    List<E> expectedList = new ArrayList<>();
    expectedList.add(expected);

    // simulate behaviour
    doThrow(new EntityExistsException()).
            doThrow(new IllegalArgumentException()).
            doThrow(new TransactionRequiredException()).
            doNothing().
            doNothing().
            when(em).persist(anyObject());
    doThrow(new PersistenceException()).
            doThrow(new TransactionRequiredException()).
            when(em).flush();

    // act
    // throws EntityExistsException
    cut.batchCreate(expectedList.iterator());

    // throws IllegalArgumentException
    cut.batchCreate(expectedList.iterator());

    // throws TransactionRequiredException
    cut.batchCreate(expectedList.iterator());

    // throws PersistenceException
    cut.batchCreate(expectedList.iterator());

    // throws TransactionRequiredException
    cut.batchCreate(expectedList.iterator());

    // verify all methods are called
    verify(em, times(5)).persist(anyObject());
    verify(em, times(2)).flush();
  }

  @Test
  public void testBatchEdit() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    List<E> expectedList = new ArrayList<>();
    expectedList.add(expected);
    // simulate behaviour
    doReturn(expected).when(em).merge(anyObject());
    doNothing().when(em).flush();

    // act
    cut.batchEdit(expectedList.iterator());

    // verify all methods are called
    verify(em).merge(anyObject());
    verify(em).flush();
  }

  @Test
  public void testBatchEditFail() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    List<E> expectedList = new ArrayList<>();
    expectedList.add(expected);

    // simulate behaviour
    doThrow(new EntityExistsException()).
            doThrow(new IllegalArgumentException()).
            doThrow(new TransactionRequiredException()).
            doReturn(expected).
            doReturn(expected).
            when(em).merge(anyObject());
    doThrow(new PersistenceException()).
            doThrow(new TransactionRequiredException()).
            when(em).flush();

    // act
    // throws EntityExistsException
    cut.batchEdit(expectedList.iterator());

    // throws IllegalArgumentException
    cut.batchEdit(expectedList.iterator());

    // throws TransactionRequiredException
    cut.batchEdit(expectedList.iterator());

    // throws PersistenceException
    cut.batchEdit(expectedList.iterator());

    // throws TransactionRequiredException
    cut.batchEdit(expectedList.iterator());

    // verify all methods are called
    verify(em, times(5)).merge(anyObject());
    verify(em, times(2)).flush();
  }

  @Test
  public void testBatchRemove() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    List<E> expectedList = new ArrayList<>();
    expectedList.add(expected);
    // simulate behaviour
    doNothing().when(em).remove(anyObject());
    doNothing().when(em).flush();

    // act
    cut.batchRemove(expectedList.iterator());

    // verify all methods are called
    verify(em).remove(anyObject());
    verify(em).flush();
  }

  @Test
  public void testBatchRemoveFail() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    List<E> expectedList = new ArrayList<>();
    expectedList.add(expected);

    // simulate behaviour
    doThrow(new EntityExistsException()).
            doThrow(new IllegalArgumentException()).
            doThrow(new TransactionRequiredException()).
            doNothing().
            doNothing().
            when(em).remove(anyObject());
    doThrow(new PersistenceException()).
            doThrow(new TransactionRequiredException()).
            when(em).flush();

    // act
    // throws EntityExistsException
    cut.batchRemove(expectedList.iterator());

    // throws IllegalArgumentException
    cut.batchRemove(expectedList.iterator());

    // throws TransactionRequiredException
    cut.batchRemove(expectedList.iterator());

    // throws PersistenceException
    cut.batchRemove(expectedList.iterator());

    // throws TransactionRequiredException
    cut.batchRemove(expectedList.iterator());

    // verify all methods are called
    verify(em, times(5)).remove(anyObject());
    verify(em, times(2)).flush();
  }

  @Test
  public void testFind() {
    E expected = null;
    try {
      expected = (E) entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(BaseRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
    }

    // simulate behaviour
    doReturn(expected).when(em).find(eq(entityClass), anyObject());

    // act
    E result = cut.find(1);
    Assert.assertEquals(result, expected);

    // verify all methods are called
    verify(em).find(eq(entityClass), anyObject());
  }

  @Test
  public void testFindFail() {
    E expected = null;

    // simulate behaviour
    doThrow(new IllegalArgumentException()).
            when(em).find(eq(entityClass), anyObject());

    // act
    // throws TransactionRequiredException
    E result = cut.find(1);
    Assert.assertEquals(result, expected);

    // throws IllegalArgumentException
    result = cut.find(1);
    Assert.assertEquals(result, expected);

    // verify all methods are called
    verify(em, times(2)).find(eq(entityClass), anyObject());
  }

}
