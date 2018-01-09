<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
			<%--<div class="img-backdrop" style="background-image: url('${contextPath}/resources/img/img16.jpg')"></div> --%>
			<div class="card contain-sm style-transparent">
				<div class="card-body">
					<div class="row">
						<div class="col-sm-6">
							<span class="text-lg text-bold text-primary">
								<img src='${contextPath}/resources/img/codespot-1.png'/>
							</span>
							<br/>
							<br/>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<h3 class="text-primary">Congratulations!</h3>
							</div>
							<div class="col-lg-12">
								<article class="margin-bottom-xxl">
									<p class="text-lg">
										Dear <label class="text-success">Ashutosh</label>${user.userName}, You have been successfully registered with Codespot.<br/>
										To complete the signup process you have to verify you email account. We have sent you a verification email.
										Please click on the link provided in the email to activate your codespot account
									</p>
									<div class="">
										<br/>
									</div>
									<p class="text-lg">Go back to 
										<label class="text-success success-dark">
											<a href="${contextPath}/login">
												Login page
											</a>
										</label>
									</p>
								</article>
							</div><!--end .col -->
						</div>
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
