<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
	<head>
		
		<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/images/icons/codespot.ico">
	    <title><spring:message code="app.name" /></title>

		<!-- BEGIN META -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="keywords" content="your,keywords">
		<meta name="description" content="Short explanation about this website">
		<!-- END META -->

		<!-- BEGIN STYLESHEETS -->
		<link href='http://fonts.googleapis.com/css?family=Roboto:300italic,400italic,300,400,500,700,900' rel='stylesheet' type='text/css'/>
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/bootstrap.css?1422792965" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/materialadmin.css?1425466319" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/font-awesome.min.css?1422529194" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/material-design-iconic-font.min.css?1421434286" />
		<!-- END STYLESHEETS -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script type="text/javascript" src="${contextPath}/resources/js/libs/utils/html5shiv.js?1403934957"></script>
		<script type="text/javascript" src="${contextPath}/resources/js/libs/utils/respond.min.js?1403934956"></script>
		<![endif]-->
	</head>
	<body class="menubar-hoverable header-fixed ">

		<!-- BEGIN LOGIN SECTION -->
		<section class="section-account">
			<div class="img-backdrop" style="background-image: url('${contextPath}/resources/img/img16.jpg')"></div>
			<div class="spacer"></div>
			<div class="card contain-sm style-transparent">
				<div class="card-body">
					<div class="row">
						<div class="col-sm-6">
							<br/>
							<span class="text-lg text-bold text-primary"><img src='${contextPath}/resources/img/codespot-1.png'/></span>
							<br/><br/>
							<form:form modelAttribute="user"  class="form form-validate floating-label" novalidate="novalidate" action="${contextPath}/users/signup" accept-charset="utf-8" method="post">
								<div class="form-group">
									<form:input path="userEmail" type="email" class="form-control" id="signup_email" name="email" required="true" />
									<label for="email">Email</label>
									<div class="help-block col-xs-12 col-sm-reset">
										<form:errors path="userEmail" class="text-danger" />
									</div>
								</div>
								<div class="form-group">
									<form:input type="text" path="userName" class="form-control" id="signup_username" name="username" data-rule-rangelength="[6, 20]" required="true" />
									<label for="username">Username</label>
									<div class="help-block col-xs-12 col-sm-reset">
										<form:errors path="userName" class="text-danger" />
									</div>
								</div>
								<div class="form-group">
									<form:input type="password" path="userPassword" class="form-control" id="signup_password" name="password" data-rule-rangelength="[8, 20]" required="true" />
									<label for="password">Password</label>
									<div class="help-block col-xs-12 col-sm-reset">
										<form:errors path="userPassword" class="text-danger">error </form:errors>
									</div>
								</div>
								<div class="form-group">
									<form:input type="password" path="confirmPassword" class="form-control" id="signup_password_confirm" name="password-confirm" data-rule-equalTo="#signup_password" required="true" />
									<label for="password">Confirm Password</label>
									<div class="help-block col-xs-12 col-sm-reset">
										<form:errors path="confirmPassword" class="text-danger" />
									</div>
								</div>
								<br/>
								<div class="row">
									<div class="col-xs-6 text-left">
										<div class="checkbox checkbox-inline checkbox-styled">
											<label>
												<input type="checkbox"> <span>Remember me</span>
											</label>
										</div>
									</div><!--end .col -->
									<div class="col-xs-6 text-right">
										<button class="btn btn-primary btn-raised" type="submit">Signup</button>
									</div><!--end .col -->
								</div><!--end .row -->
							</form:form>
						</div><!--end .col -->
						<div class="col-sm-5 col-sm-offset-1 text-center">
							<br><br>
								<h3 class="text-light">
									Already have an account ?
								</h3>
								<a class="btn btn-block btn-raised btn-primary" href="${contextPath}/login">Login here</a>
								<br><br>
									<h3 class="text-light">
										or
									</h3>
									<p>
										<a href="#" class="btn btn-block btn-raised btn-info"><i class="fa fa-facebook pull-left"></i>Login with Facebook</a>
									</p>
									<p>
										<a href="#" class="btn btn-block btn-raised btn-info"><i class="fa fa-twitter pull-left"></i>Login with Twitter</a>
									</p>
								</div><!--end .col -->
							</div><!--end .row -->
						</div><!--end .card-body -->
					</div><!--end .card -->
				</section>
				<!-- END LOGIN SECTION -->

				<!-- BEGIN JAVASCRIPT -->
				<script src="${contextPath}/resources/js/libs/jquery/jquery-1.11.2.min.js"></script>
				<script src="${contextPath}/resources/js/libs/jquery/jquery-migrate-1.2.1.min.js"></script>
				<script src="${contextPath}/resources/js/libs/bootstrap/bootstrap.min.js"></script>
				<script src="${contextPath}/resources/js/libs/spin.js/spin.min.js"></script>
				<script src="${contextPath}/resources/js/libs/autosize/jquery.autosize.min.js"></script>
				<script src="${contextPath}/resources/js/libs/nanoscroller/jquery.nanoscroller.min.js"></script>
				<script src="${contextPath}/resources//js/libs/jquery-validation/dist/jquery.validate.min.js"></script>
				<script src="${contextPath}/resources//js/libs/jquery-validation/dist/additional-methods.min.js"></script>
				<script src="${contextPath}/resources/js/core/source/App.js"></script>
				<script src="${contextPath}/resources/js/core/source/AppNavigation.js"></script>
				<script src="${contextPath}/resources/js/core/source/AppOffcanvas.js"></script>
				<script src="${contextPath}/resources/js/core/source/AppCard.js"></script>
				<script src="${contextPath}/resources/js/core/source/AppForm.js"></script>
				<script src="${contextPath}/resources/js/core/source/AppNavSearch.js"></script>
				<script src="${contextPath}/resources/js/core/source/AppVendor.js"></script>
				<script src="${contextPath}/resources/js/core/demo/Demo.js"></script>
				<!-- END JAVASCRIPT -->

			</body>
		</html>
