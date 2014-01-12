package models.enums;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum DataSource {
	INSTANCE;

	private DataSource() {
		this.emf = Persistence.createEntityManagerFactory("person-list");
	}

	private EntityManagerFactory emf;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
