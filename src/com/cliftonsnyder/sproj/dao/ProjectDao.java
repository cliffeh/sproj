package com.cliftonsnyder.sproj.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cliftonsnyder.sproj.Urgency;
import com.cliftonsnyder.sproj.model.Project;

public enum ProjectDao {

	instance;
	
	public static final String PERSISTENCE_UNIT_NAME = "project";
	private Map<String, Project> contentProvider = new HashMap<String, Project>();
	private EntityManagerFactory factory;

	// TODO this is (close to) the code you need; take it out of main(), use it for ProjectDao...
	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		// Read the existing entries and write to console
		Query q = em.createQuery("select p from project p");
		List<Project> projList = q.getResultList();
		for (Project proj : projList) {
			// System.out.println(todo);
			contentProvider.put("" + proj.getId(), proj);
		}
		// System.out.println("Size: " + todoList.size());

		// Create new todo
		// em.getTransaction().begin();
		// Todo todo = new Todo();
		// todo.setSummary("This is a test");
		// todo.setDescription("This is a test");
		// em.persist(todo);
		// em.getTransaction().commit();

		em.close();
	}

	private ProjectDao() {

		// factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		Project project = new Project(1);
		project.setName("testes1");
		project.setOwner("cliff1");
		project.setStatus("swimming along");
		project.setUrgency(Urgency.OKAY);
		contentProvider.put("1", project);

		project = new Project(2);
		project.setName("testes2");
		project.setOwner("jimmy");
		project.setStatus("getting iffy");
		project.setUrgency(Urgency.URGENT);
		contentProvider.put("2", project);

		project = new Project(3);
		project.setName("testes3");
		project.setOwner("jonas");
		project.setStatus("mayday! mayday!");
		project.setUrgency(Urgency.CRITICAL);
		contentProvider.put("3", project);
	}

	public Map<String, Project> getModel() {
		return contentProvider;
	}

}
