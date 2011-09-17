function sproj_init() {
	load_projects();
}

function load_projects() {
	$.ajax( {
		type : "GET",
		url : "/sproj/rest/projects",
		success : function(xml) {
			populate_projects(xml)
		}
	});

}

function populate_projects(xml) {
	// function(xml) {
	$(xml).find('project').each(function() {
		var id = $(this).find("id").text();
		var name = $(this).find("name").text();
		var owner = $(this).find("owner").text();
		var status = $(this).find("status").text();
		var urgency = $(this).find("urgency").text();

		var project = create_project(id, name, owner, status, urgency);
		$("#projects").append(project);
		// $("#projects").append("***" + );
		});
	// }
}

function create_project(id, name, owner, status, urgency) {
	var project = $("<tr/>").addClass("project");
	project.append($("<td/>").addClass("id").text(id));
	project.append($("<td/>").addClass("name").text(name));
	project.append($("<td/>").addClass("owner").text(owner));
	project.append($("<td/>").addClass("status").text(status));
	project.append($("<td/>").addClass("urgency").text(urgency));
	return project;
}

function toggle_add(event){
	if($("#addProjectFields").css("visibility") == "hidden"){
		$("#addProjectFields").css("visibility", "visible");
		event.target.value = "-";
	}else{
		$("#addProjectFields").css("visibility", "hidden");
		event.target.value = "+";
	}
}