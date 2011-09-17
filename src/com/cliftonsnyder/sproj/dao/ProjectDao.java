package com.cliftonsnyder.sproj.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cliftonsnyder.sproj.model.Project;

public enum ProjectDao {

	instance;

	public static final String PERSISTENCE_UNIT_NAME = "project";
	private EntityManager em;

	// private Map<Integer, Project> projectMap;

	private ProjectDao() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		// projectMap = new Hashtable<Integer, Project>();
		// em = factory.createEntityManager();
		// Query q = em.createQuery("select p from project p");
		//
		// @SuppressWarnings("unchecked")
		// List<Project> projectList = (List<Project>) q.getResultList();
		// for (Project proj : projectList) {
		// projectMap.put(proj.getId(), proj);
		// }

	}

	private Collection<Project> getProjects() {
		Query q = em.createQuery("select p from project p");
		@SuppressWarnings("unchecked")
		List<Project> projectList = (List<Project>) q.getResultList();
		return projectList;
	}

	public Collection<Project> getProjectList() {
		return getProjects();
	}

	public Project getProject(int id) {
		Query q = em.createQuery("select p from project p where p.id = :id")
				.setParameter("id", id);
		Project project = (Project) q.getSingleResult();
		return project;
	}

	public Project removeProject(int id) {
		Project project = getProject(id);
		em.getTransaction().begin();
		em.remove(project);
		em.getTransaction().commit();
		return project;
	}

	public boolean hasProject(int id) {
		Query q = em.createQuery("select p from project p where id=" + id);
		return q.getResultList().size() > 0;
	}

	public Project putProject(Project project) {
		em.getTransaction().begin();
		em.persist(project);
		em.getTransaction().commit();
		return getProject(project.getId());
		// return project;
	}
}
