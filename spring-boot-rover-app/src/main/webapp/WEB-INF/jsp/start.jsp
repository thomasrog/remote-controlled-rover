<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/3.0.3/css/bootstrap.min.css" />

<style type="text/css">
.img-overlay {
	position: relative;
	max-width: 500px;
}

.project-overlay {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	
}
</style>
</head>


<body>
	<script type="text/javascript"
		src="/webjars/jquery/2.0.3/jquery.min.js"></script>
	<div id="navbar" class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="https://github.com/spring-projects/spring-boot"> Spring
				Boot </a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="/"> Home </a></li>
			</ul>
		</div>
	</div>
	<div id="mainPane" class="jumbotron">
		<div class="img-overlay">
			<img src="http://<%=request.getLocalAddr()%>:8080/?action=stream/mjpg"
				class="img-responsive" />
			<div id="innerMain" class="project-overlay"></div>
		</div>

	</div>
	<script type="text/javascript"
		src="/webjars/bootstrap/3.0.3/js/bootstrap.min.js"></script>

	<script src="/webjars/jquery/2.0.3/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('p').animate({
				fontSize : '48px'
			}, "slow");
		});
	</script>

	<script src="/js/raphael-min.js"></script>
	<!-- credits to: https://github.com/mattes/joystick-js -->
	<script src="/js/joystick.jquery.js"></script>
	<script>
		console.log("start");

		$('#innerMain').joystick({
			"width" : 950,
			"height" : 600,
			"pathColor" : "red",
			"virtualPositionEasing" : "linear",
			"virtualPositionMaxValue" : 10
		}, positionCallback);
		function positionCallback(x, y) {
			_x = x;
			_y = y;

			console.log("x : " + x);
			console.log("y : " + y);

			$.ajax({
				url : 'joystick/',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify({
					"x" : x,
					"y" : y
				}),
				dataType : 'json'
			});
		}
	</script>
</body>



</html>
