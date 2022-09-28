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
<title>View Messages</title>
</head>
<body>


	<div class="bg-image"
		style="background-image: url('https://t3.ftcdn.net/jpg/04/36/70/98/360_F_436709813_il2F0kTLPerKC8a3pxOMwDC26G1WjPfI.jpg'); height: 100vh">


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




	<div class="card mx-auto w-25 mt-5 text-center">
		<div class="card-body bg-secondary text-white">
			<h4 class="card-title text-center">Message Board</h4>
			<p class="card-text text-center">Communicate with each other here</p>
			<a class="btn btn-info " href="/messages/add">add comment</a>
		</div>
	</div>



	<table
		class="table table-light table-hover table-bordered mx-auto mt-5 w-50 text-center">
		<thead>
			<tr>
				<th>Posted By</th>
				<th>Message</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="eachComment" items="${commentList }">
				<tr>
					<td><c:out value="${eachComment.user.firstName}" /></td>
					<td><c:out value="${eachComment.comment}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	</div>





</body>
</html>