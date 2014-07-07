package com.welovecoding.tutorial.data.statistic.entity;

import static com.welovecoding.tutorial.data.statistic.entity.Statistic.STATISTIC_TYPE_COLUMN_NAME;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Michael Koppen
 */
//@Table(
//        uniqueConstraints
//        = @UniqueConstraint(columnNames = {STATISTIC_TYPE_COLUMN_NAME, "ID"})
//)
@Entity
@DiscriminatorColumn(name = STATISTIC_TYPE_COLUMN_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Statistic implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final String STATISTIC_TYPE_COLUMN_NAME = "STATISTIC_TYPE";

  @GeneratedValue(strategy = GenerationType.TABLE)
  @Id
  private Long id;
  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String name;

  @NotNull
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date fromDate;

  @NotNull
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date toDate;

  @NotNull
  private long duration;

  @Column(name = STATISTIC_TYPE_COLUMN_NAME, insertable = false, updatable = false)
  private String type;

  @NotNull
  private long hits;

  public Statistic() {
  }

  public Statistic(String name, int hits) throws Exception {
    this(name, new Date(), 0, TimeUnit.MICROSECONDS, hits);
  }

  public Statistic(String name, Date from, Date to, int hits) throws Exception {
    this.name = name;
    this.fromDate = from;
    this.toDate = to;
    if (fromDate.after(toDate)) {
      throw new Exception("Invalid DateRange!");
    }
    if (fromDate.equals(toDate)) {
      this.duration = 0;
    } else {
      this.duration = toDate.getTime() - fromDate.getTime();
    }
    this.hits = hits;
  }

  public Statistic(String name, Date from, long duration, TimeUnit unit, int hits) throws Exception {
    this.name = name;
    this.fromDate = from;
    this.toDate = new Date(from.getTime() + unit.toMillis(duration));
    if (fromDate.after(toDate) || duration < 0) {
      throw new Exception("Invalid DateRange!");
    }
    this.duration = unit.toMillis(duration);
    this.hits = hits;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public long getHits() {
    return hits;
  }

  public void setHits(long hits) {
    this.hits = hits;
  }

}
