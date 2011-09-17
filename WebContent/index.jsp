<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/sproj.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="js/sproj.js"></script>
<title>sproj</title>
</head>
<body onload="sproj_init()">
<div id="outer">
<div>
<table id="projects">
	<tr>
		<th align="left">id</th>
		<th align="left">name</th>
		<th align="left">owner</th>
		<th align="left">status</th>
		<th align="left">urgency</th>
	</tr>
</table>
</div>
<div id="addProject"><input value="+" id="addButton" type="button"
	onclick="toggle_add(event)">
<div id="addProjectFields">
<form action="/sproj/rest/projects" method="post">name: <input
	name="name" /><br />
owner: <input name="owner" /><br />
status: <input name="status" /><br />
urgency: <input name="urgency" /><br />
<input type="submit" /></form>
</div>
</div>
</div>
</body>
</html>