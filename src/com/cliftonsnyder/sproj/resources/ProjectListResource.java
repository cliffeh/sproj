package com.cliftonsnyder.sproj.resources;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.cliftonsnyder.sproj.dao.ProjectDao;
import com.cliftonsnyder.sproj.model.Project;

// Will map the resource to the URL projects
@Path("/projects")
public class ProjectListResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the Collection of Projects to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Collection<Project> getProjectsBrowser() {
		return ProjectDao.instance.getProjectList();
	}

	// Return the list of Projects for applications
	@GET
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Project> getProjects() {
		return ProjectDao.instance.getProjectList();
	}

	// returns the number of Projects
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public int getCount() {
		return ProjectDao.instance.getProjectList().size();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// name owner status urgency
	public void newProject(@FormParam("name") String name,
			@FormParam("owner") String owner,
			@FormParam("status") String status,
			@FormParam("urgency") String urgency,
			@Context HttpServletResponse servletResponse) throws IOException {
		Project project = ProjectDao.instance.newProject(name, owner, status,
				Integer.parseInt(urgency));
		ProjectDao.instance.putProject(project);
		servletResponse.sendRedirect("/sproj/");
		//
		// URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		// Response.created(uri).build();
		//
		// servletResponse.sendRedirect("../create_Project.html");
	}
}
