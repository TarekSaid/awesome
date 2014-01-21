package models.daos.impl;

import models.daos.AbstractDao;
import models.impl.Person;

public class PersonDao extends AbstractDao<Person> {
  private static final long serialVersionUID = 1L;

  public PersonDao() {
    super(Person.class);
  }
}
