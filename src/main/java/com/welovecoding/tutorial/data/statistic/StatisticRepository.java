package com.welovecoding.tutorial.data.statistic;

import com.welovecoding.tutorial.data.base.BaseRepository;
import static com.welovecoding.tutorial.data.base.BaseRepository.PERSISTENCE_UNIT_NAME;
import com.welovecoding.tutorial.data.statistic.entity.Statistic;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
//    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//    CriteriaQuery<Statistic> cq = (CriteriaQuery<Statistic>) cb.createQuery(type);
//    cq.select(cq.from(type)).orderBy(cb.asc(get("STATISTIC.FROMDATE")));
    return getEntityManager().createQuery(criteriaQuery).getResultList();
  }

}
