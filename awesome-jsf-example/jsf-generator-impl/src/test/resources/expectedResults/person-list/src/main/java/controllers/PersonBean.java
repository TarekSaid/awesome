package controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import models.daos.impl.PersonDao;
import models.impl.Person;

@ManagedBean
@ViewScoped
public class PersonBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<Person> persons;
  private Person person;
  private PersonDao personDao;

  public PersonBean() {
    personDao = new PersonDao();
    init();
  }

  public void addPerson(ActionEvent event) {
    personDao.create(person);
    init();
  }

  public void editPerson(ActionEvent event) {
    personDao.update(person);
    init();
  }

  public void deletePerson(ActionEvent event) {
    personDao.delete(person);
    init();
  }

  public List<Person> getPersons() {
    return persons;
  }

  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public PersonDao getPersonDao() {
    return personDao;
  }

  public void setPersonDao(PersonDao personDao) {
    this.personDao = personDao;
  }

  private void init() {
    person = new Person();
    persons = personDao.findAll();
  }
}
