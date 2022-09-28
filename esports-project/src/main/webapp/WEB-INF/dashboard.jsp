<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>


	<div class="bg-image"
		style="background-image: url('https://dm0qx8t0i9gc9.cloudfront.net/thumbnails/video/PDaTSuJ/videoblocks-plexus-game-esport-abstract-network-business-technology-science-background-loop_hz9dmqlr8_thumbnail-1080_01.png'); height: 100vh">



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





		<div class="card text-center">
			<div class="card-body bg-light">
				<h5 class="card-title">Welcome to your Dashboard ${user.firstName}</h5>
			</div>
		</div>





		<div class="card-group mx-auto w-50 mt-5 ">
			<div class="card">
				<img
					src="https://cdn.vox-cdn.com/thumbor/qri9hvAOd3hfycSs6vXYYApBfek=/0x0:1280x720/1200x800/filters:focal(545x231:749x435)/cdn.vox-cdn.com/uploads/chorus_image/image/50095395/teaser-image-banner.0.jpg"
					class="card-img-top w-100 mx-auto " alt="...">
				<div class="card-body">
					<h5 class="card-title text-center">View Student Roster</h5>
					<p class="card-text text-center">Go here to manage all students
						currently on your roster</p>
				</div>
				<div class="card-footer text-center">
					<a class="text-muted" href="/view/roster">View Roster</a>
				</div>
			</div>
			<div class="card">
				<img
					src="https://cdn.vox-cdn.com/thumbor/qri9hvAOd3hfycSs6vXYYApBfek=/0x0:1280x720/1200x800/filters:focal(545x231:749x435)/cdn.vox-cdn.com/uploads/chorus_image/image/50095395/teaser-image-banner.0.jpg"
					class="card-img-top w-100 mx-auto" alt="...">
				<div class="card-body text-center">
					<h5 class="card-title">View Teams</h5>
					<p class="card-text">Go here to manage your current teams</p>
				</div>
				<div class="card-footer text-center">
					<a class="text-muted" href="/view/teams">View Teams</a>
				</div>
			</div>
			<div class="card">
				<img
					src="https://cdn.vox-cdn.com/thumbor/qri9hvAOd3hfycSs6vXYYApBfek=/0x0:1280x720/1200x800/filters:focal(545x231:749x435)/cdn.vox-cdn.com/uploads/chorus_image/image/50095395/teaser-image-banner.0.jpg"
					class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">View Message Board</h5>
					<p class="card-text">Go here to view messages on the
						messageboard</p>
				</div>
				<div class="card-footer text-center">
					<a class="text-muted" href="/view/messages">View Messages</a>
				</div>
			</div>
		</div>

	</div>

</body>
</html>