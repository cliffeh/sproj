package com.cliftonsnyder.sproj.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.cliftonsnyder.sproj.dao.ProjectDao;
import com.cliftonsnyder.sproj.model.Project;

@Path("/project")
public class ProjectResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public ProjectResource() {
	}

	public ProjectResource(UriInfo uriInfo, Request request) {
		this.uriInfo = uriInfo;
		this.request = request;
	}

	public ProjectResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Project getProject() {
		Project proj = ProjectDao.instance.getProject(Integer.parseInt(id));
		if (proj == null)
			throw new RuntimeException("Get: Project with id '" + id
					+ "' not found");
		return proj;
	}

	// For the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Project getProjectHTML() {
		Project proj = ProjectDao.instance.getProject(Integer.parseInt(id));
		if (proj == null)
			throw new RuntimeException("Get: Project with id '" + id
					+ "' not found");
		return proj;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putProject(JAXBElement<Project> el) {
		Project c = el.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteProject() {
		Project c = ProjectDao.instance.removeProject(Integer.parseInt(id));
		if (c == null)
			throw new RuntimeException("Delete: Project with " + id
					+ " not found");
	}

	private Response putAndGetResponse(Project project) {
		Response res;
		if (ProjectDao.instance.hasProject(project.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		ProjectDao.instance.putProject(project);
		return res;
	}

	@Path("{id}")
	public ProjectResource getProject(@PathParam("id") String id) {
		return new ProjectResource(uriInfo, request, id);
	}
}
