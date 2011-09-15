package com.cliftonsnyder.sproj.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
public class ProjectsResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of Projects to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Project> getProjectsBrowser() {
		List<Project> Projects = new ArrayList<Project>();
		Projects.addAll(ProjectDao.instance.getModel().values());
		return Projects;
	}

	// Return the list of Projects for applications
	@GET
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Project> getProjects() {
		List<Project> Projects = new ArrayList<Project>();
		Projects.addAll(ProjectDao.instance.getModel().values());
		return Projects;
	}

	// returns the number of Projects
	// Use http://localhost:8080/de.vogella.jersey.Project/rest/Projects/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = ProjectDao.instance.getModel().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newProject(@FormParam("id") String id,
			@FormParam("summary") String summary,
			@FormParam("description") String description,
			@Context HttpServletResponse servletResponse) throws IOException {
		// TODO catch NumberFormatExceptions
		Project Project = new Project(Integer.parseInt(id)); // , summary);
		if (description != null) {
			// Project.setDescription(description);
		}
		ProjectDao.instance.getModel().put(id, Project);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../create_Project.html");
	}

	// Defines that the next path parameter after Projects is
	// treated as a parameter and passed to the ProjectResources
	// Allows to type
	// http://localhost:8080/de.vogella.jersey.Project/rest/Projects/1
	// 1 will be treaded as parameter Project and passed to ProjectResource
//	@Path("{id}")
//	public ProjectResource getProject(@PathParam("id") String id) {
//		return new ProjectResource(uriInfo, request, id);
//	}

}
