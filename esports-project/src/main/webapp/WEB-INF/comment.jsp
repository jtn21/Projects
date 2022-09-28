<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Comments</title>
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


	<div class="card mx-auto w-25 mt-5">
		<div class="card-body bg-secondary text-white">
			<h4 class="card-title text-center">Add a New Message</h4>
			<p class="card-text text-center">Please fill out the form</p>
		</div>
	</div>
	
	
	<form:form class="w-50 mx-auto mt-5" method="post"
		action="/messages/add" modelAttribute="comments">
		<div class="form-group mx-5">
			<form:label path="comment"> Message </form:label>
			<form:textarea class="form-control" rows="3" path="comment" />
			<form:errors path="comment" />
		</div>
		<form:hidden path="user" value="${userId }" />
		<div class="text-center mt-5 mb-5">
			<button class="form-group btn btn-success" type="submit">Submit</button>
			<a class="btn btn-danger" href="/dashboard">Cancel</a>
		</div>

	</form:form>






</body>
</html>