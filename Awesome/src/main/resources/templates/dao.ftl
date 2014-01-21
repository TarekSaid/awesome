package models.daos;

import java.util.List;

import models.Identifiable;

public interface Dao<T extends Identifiable> {
  public void create(T t);
  public T find(T t);
  public List<T> findAll();
  public void update(T t);
  public void delete(T t);
}
