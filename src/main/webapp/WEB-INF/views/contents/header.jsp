<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<header id="header" >
			<div class="headerbar">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="headerbar-left">
					<ul class="header-nav header-nav-options">
						<li class="header-nav-brand" >
							<div class="brand-holder">
								<a href="${contextPath}/">
								<span class="text-lg text-bold text-primary"><img src='${contextPath}/resources/img/codespot-1.png'/></span>
								</a>
							</div>
						</li>
						<!-- <li>
							<a class="btn btn-icon-toggle menubar-toggle" data-toggle="menubar" href="javascript:void(0);">
								<i class="fa fa-bars"></i>
							</a>
						</li> -->
					</ul>
					
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->

			<div class="headerbar-right">
				<ul class="header-nav header-nav-profile">
					<li>
						<form class="navbar-search" role="search">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-content" id="codespotsearch">
										<!-- <input type="text" class="form-control" id="groupbutton9"  placeholder="Search..." onfocus="codespotsearchFocusIn()" onfocusout="codespotsearchFocusOut()" style="padding-left:3px;"> -->
										<input type="text" class="form-control"
											placeholder="Search...">
									</div>
									<div class="input-group-btn">
										<button class="btn btn-default" type="button">
											<span class="glyphicon glyphicon-search text-success"
												id="basic-addon2"></span>
										</button>
									</div>
								</div>
							</div>
						</form>
					</li>
					<security:authorize access="hasRole('ROLE_ANONYMOUS')">
						<li><a href="${contextPath}/login"
							class=".navbar-btn btn ink-reaction btn-flat btn-success"
							style="text-transform: none;"> Log In </a></li>
						<li><a href="${contextPath}/users/signup"
							class=".navbar-btn codespot-btn-signup btn btn-block ink-reaction btn-raised btn-success"
							style="text-transform: none;"> Sign Up </a></li>
					</security:authorize>

					<security:authorize access="hasRole('ROLE_USER')">
						<li class="dropdown"><a href="javascript:void(0);" class="dropdown-toggle ink-reaction" data-toggle="dropdown">
							<img src="${contextPath}/resources/img/avtars/a (10).jpg" alt="" /> 
							<span class="profile-info"> 
								<p class="navbar-text"><security:authentication property="principal.username" /></p>
							</span>
						</a>
							<ul class="dropdown-menu animation-dock">
								<li class="dropdown-header">Config</li>
								<li><a href="#">My profile</a></li>
								<li><a href="#">My blog <span class="badge style-danger pull-right">16</span></a></li>
								<li><a href="#">My appointments</a></li>
								<li class="divider"></li>
								<li><a href="#"><i class="fa fa-fw fa-lock"></i> Lock</a></li>
								<li><a href="${contextPath}/logout"><i class="fa fa-fw fa-power-off text-danger"></i> Logout</a></li>
							</ul> <!--end .dropdown-menu --></li>
					</security:authorize>
				</ul>

				<%-- <ul class="header-nav header-nav-profile">
						<li class="dropdown">
							<a href="javascript:void(0);" class="dropdown-toggle ink-reaction" data-toggle="dropdown">
								<img src="${contextPath}/resources/img/avatar1.jpg?1403934956" alt="" />
								<span class="profile-info">
									${loggedUsername}
									<!-- <small>Administrator</small> -->
								</span>
							</a>
							<ul class="dropdown-menu animation-dock">
								<li class="dropdown-header">Config</li>
								<li><a href="../../../html/pages/profile.html">My profile</a></li>
								<li><a href="../../../html/pages/blog/post.html">My blog <span class="badge style-danger pull-right">16</span></a></li>
								<li><a href="../../../html/pages/calendar.html">My appointments</a></li>
								<li class="divider"></li>
								<li><a href="../../../html/pages/locked.html"><i class="fa fa-fw fa-lock"></i> Lock</a></li>
								<li><a href="../../../html/pages/login.html"><i class="fa fa-fw fa-power-off text-danger"></i> Logout</a></li>
							</ul><!--end .dropdown-menu -->
						</li><!--end .dropdown -->
					</ul> --%>
				<!--end .header-nav-profile -->


				<!-- off canvas-chat -->
				<ul class="header-nav header-nav-toggle">
						<li>
							<a class="btn btn-icon-toggle btn-default" href="#offcanvas-search" data-toggle="offcanvas" data-backdrop="false">
								<i class="fa fa-comment"></i>
							</a>
						</li>
					</ul>
				<!--end .header-nav-toggle -->
			</div>
			<!--end #header-navbar-collapse -->
			</div>
		</header>