<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/3.0.3/css/bootstrap.min.css" />
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
		<h1>Home</h1>
		<p>Some static content</p>
		<img src="http://192.168.178.23:8080/?action=stream/mjpg">
		<p>
			<a class="btn btn-lg btn-primary" href="#navbar" role="button">Go
				&raquo;</a>
		</p>
		<div id="result"></div>
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



	<script src="/js/virtualjoystick.js"></script>
	<script>
		console.log("touchscreen is",
				VirtualJoystick.touchScreenAvailable() ? "available"
						: "not available");

		var joystick = new VirtualJoystick({
			container : document.getElementById('mainPane'),
			mouseSupport : true,
		});
		joystick.addEventListener('touchStart', function() {
			console.log('down')
		})
		joystick.addEventListener('touchEnd', function() {
			console.log('up')
		})

		setInterval(function() {
			var outputEl = document.getElementById('result');
			outputEl.innerHTML = '<b>Result:</b> ' + ' dx:' + joystick.deltaX()
					+ ' dy:' + joystick.deltaY()
					+ (joystick.right() ? ' right' : '')
					+ (joystick.up() ? ' up' : '')
					+ (joystick.left() ? ' left' : '')
					+ (joystick.down() ? ' down' : '')
		}, 1 / 30 * 1000);
	</script>
</body>



</html>
