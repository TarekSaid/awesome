package models.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import models.Identifiable;
import models.enums.DataSource;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T>, Serializable {
  private static final long serialVersionUID = 1L;
  private Class<T> type;

  public AbstractDao(Class<T> type) {
    super();
    this.type = type;
  }

  public void create(T t) {
    EntityManager em = DataSource.INSTANCE.getEntityManager();

    try {
      em.getTransaction().begin();
      em.persist(t);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  public T find(T t) {
    EntityManager em = DataSource.INSTANCE.getEntityManager();

    try {
      em.getTransaction().begin();
      return em.find(this.type, t);
    } finally {
      em.close();
    }
  }

  public List<T> findAll() {
    EntityManager em = DataSource.INSTANCE.getEntityManager();

    try {
          CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(this.type);
          CriteriaQuery<T> all = cq.select(cq.from(this.type));

          return em.createQuery(all).getResultList();
    } finally {
      em.close();
    }
  }

  public void update(T t) {
    EntityManager em = DataSource.INSTANCE.getEntityManager();

    try {
      em.getTransaction().begin();
      em.merge(t);
    } finally {
      em.getTransaction().commit();
      em.close();
    }
  }

  public void delete(T t) {
    EntityManager em = DataSource.INSTANCE.getEntityManager();

    try {
      em.getTransaction().begin();
      T obj = em.getReference(this.type, t.getId());
      em.remove(obj);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }
}
