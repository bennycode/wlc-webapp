package com.welovecoding.tutorial.data.statistic;

import com.welovecoding.tutorial.data.base.BaseRepository;
import static com.welovecoding.tutorial.data.base.BaseRepository.PERSISTENCE_UNIT_NAME;
import com.welovecoding.tutorial.data.statistic.entity.Statistic;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

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

  public List<? extends Statistic> findAllByType(Class<? extends Statistic> type) {
    CriteriaQuery<Statistic> cq = (CriteriaQuery<Statistic>) getEntityManager().getCriteriaBuilder().createQuery(type);
    cq.select(cq.from(type));
    return getEntityManager().createQuery(cq).getResultList();
  }

}
