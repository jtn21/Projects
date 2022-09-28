<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Teams</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/dashboard">Dashboard</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/dashboard">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="/students/add">Add
							student</a></li>
					<li class="nav-item"><a class="nav-link" href="/teams/add">Create
							Team</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout">Log
							out</a></li>
				</ul>
			</div>
		</div>
	</nav>




	<table
		class="table table-light table-hover table-bordered mx-auto mt-5 w-50 text-center">
		<thead>
			<tr>
				<th>Team Name</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="eachTeam" items="${teamList }">
				<tr>
					<td><c:out value="${eachTeam.teamName}" /></td>
					<td><a class="btn btn-info"
						href="/students/edit/${eachStudent.id }">Edit</a> <a
						class="btn btn-danger" href="/students/delete/${eachStudent.id }">Delete</a>
					</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>










</body>
</html>