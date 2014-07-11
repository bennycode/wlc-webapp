package com.welovecoding.tutorial.data.statistic;

import com.welovecoding.tutorial.data.base.BaseRepository;
import static com.welovecoding.tutorial.data.base.BaseRepository.PERSISTENCE_UNIT_NAME;
import com.welovecoding.tutorial.data.statistic.entity.Statistic;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class StatisticRepository extends BaseRepository<Statistic> {

  public StatisticRepository() {
    super(Statistic.class);
  }

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public List<Statistic> findAllByType(Class<? extends Statistic> type) {
    CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Statistic> criteriaQuery = (CriteriaQuery<Statistic>) criteriaBuilder.createQuery(type);

    Root<Statistic> from = (Root<Statistic>) criteriaQuery.from(type);
    CriteriaQuery<Statistic> select = criteriaQuery.select(from);

    select.orderBy(criteriaBuilder.asc(from.get("fromDate")));
    return getEntityManager().createQuery(criteriaQuery).getResultList();
  }

  public List<Statistic> findAllBetweenByType(Class<? extends Statistic> type, Date fromDate, Date toDate) {
    CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
    // Build Query
    CriteriaQuery<Statistic> query = (CriteriaQuery<Statistic>) builder.createQuery(type);

    //FROM
    Root<Statistic> rootTable = (Root<Statistic>) query.from(type);
    //SELECT
    CriteriaQuery<Statistic> select = query.select(rootTable);

    // PARAMETER
    ParameterExpression<Date> fromDateExp = builder.parameter(Date.class, "fromDate");
    ParameterExpression<Date> toDateExp = builder.parameter(Date.class, "toDate");

    //WHERE
    select.where(
            builder.and(
                    builder.greaterThanOrEqualTo(rootTable.<Date>get("fromDate"), fromDateExp),
                    builder.lessThanOrEqualTo(rootTable.<Date>get("toDate"), toDateExp)
            )
    );

    //ORDERBY
    select.orderBy(builder.asc(rootTable.<Date>get("fromDate")));

    // Build final query with params
    TypedQuery<Statistic> finalQuery = getEntityManager().createQuery(query).
            setParameter(fromDateExp, fromDate).
            setParameter(toDateExp, toDate);

    return finalQuery.getResultList();
  }

}
