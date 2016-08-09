<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>login jsp</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width" />
<base href="/" />
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resources/css/style.css">
<link rel="stylesheet" media="(max-width: 800px)"
	href="/resources/css/xs.css" />
<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body class="fade-out body-bg">
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-12">
				<div class="col-xs-12 col-sm-7 col-lg-9 col-md-8">
					<div class="title-box">
						<img class="img-responsive"
							src="/resources/images/magneto-logo.png"
							style="margin-top: 30px;">
						<p class="text-size">Meet new friends online : Make new
							friends: Find a friend Meet new single people online chat and
							find new friends to socialise with today!Meet new single people
							online chat and find new friends to socialise with today!</p>
					</div>

				</div>
				<div class="col-xs-12 col-sm-5 col-lg-3 col-md-4">


					<form class="login-form"  action="/login" method="post">
						<h1>Sign in with your email</h1>
						<div class="field">
							<input type="email" required placeholder="Email" name="username" />
						</div>
						<div class="field">
							<input type="password" required minlength=4 placeholder="Password" name="password" />
						</div>
						<footer>
							<div class="float-right">
							<input type="submit" class="action-botton" value="Sign in" />
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> 
							<a href="/register"
							class="register">Create a new account</a>
						
						</footer>
					
						<!--   <h1>With a social network</h1> -->
						<!--  <a href="/login/facebook" class="fb">Fb Login</a>
                     <a href="/login/github" class="git">Github Login</a>
				     <button class="google">G+ Login</button> -->
					</form>
				</div>
			</div>
		</div>
	</div>
</html>