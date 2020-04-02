<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<!--Made with love by Mutiullah Samim -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!--Bootsrap 4 CDN-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!--Fontawesome CDN-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<!--Custom styles-->
<link rel="stylesheet" type="text/css" href="css/loginstyle.css">
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
			
				<div class="card-header">
					<h3>Sign Up</h3>
				</div>
				
				<div class="card-body">
					<form action="RegisterStudent" method = "post" >
						<div class="input-group form-group">
							
							<input type="text" class="form-control" name="reg" placeholder="Registration Number">

						</div>
						<div class="input-group form-group">
							
							<input type="password" class="form-control"
								name = "password" placeholder="Password">
						</div>
						<div class="input-group form-group">
							
							<input type="text" class="form-control" name="name" placeholder="Name">

						</div>
						<div class="input-group form-group">
							
							<input type="text" class="form-control" name="email" placeholder="email">

						</div>
						<div class="form-group">
							<input type="submit" value="Register"
								class="btn float-right login_btn">
						</div>
					</form>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>