<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<header id="header">
	<div class="headerbar">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="headerbar-left">
			<ul class="header-nav header-nav-options">
				<li class="header-nav-brand">
					<div class="brand-holder">
						<a href="${contextPath}/"> <span
							class="text-lg text-bold text-primary"><img
								src='${contextPath}/resources/img/codespot-1.png' /></span>
						</a>
					</div>
				</li>
				<li><a class="btn btn-icon-toggle menubar-toggle"
					data-toggle="menubar" href="javascript:void(0);"> <i
						class="fa fa-bars"></i>
				</a></li>
			</ul>
		</div>
		<div class="headerbar-right">
			<ul class="header-nav header-nav-options">
				<security:authorize
					access="hasAnyRole('ROLE_ANONYMOUS', 'ROLE_USER')">
					<li>
						<!-- Search form -->
						<form class="navbar-search" role="search">
							<div class="form-group">
								<input type="text" class="form-control" name="headerSearch"
									placeholder="Enter your keyword">
							</div>
							<button type="submit" class="btn btn-icon-toggle ink-reaction">
								<i class="fa fa-search"></i>
							</button>
						</form>
					</li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_USER')">
					<li class="dropdown hidden-xs"><a href="javascript:void(0);"
						class="btn btn-icon-toggle btn-default"
						data-toggle="dropdown"> <i class="fa fa-bell" id="notify"></i>
						<sup class="badge style-danger">4</sup>
					</a>
						<ul class="dropdown-menu animation-expand" id="userNotification">
							<li class="dropdown-header">Today's messages</li>
							<!-- <li>
								<a class="alert alert-callout alert-warning"
									href="javascript:void(0);"> <img
									class="pull-right img-circle dropdown-avatar"
									src="#" alt="" /> <strong>Alex Anistor</strong><br /> <small>Testing functionality...</small>
								</a>
							</li> -->
							<!-- <li><a class="alert alert-callout alert-info"
								href="javascript:void(0);"> <img
									class="pull-right img-circle dropdown-avatar"
									src="../../../assets/img/avatar3.jpg?1404026799" alt="" /> <strong>Alicia
										Adell</strong><br /> <small>Reviewing last changes...</small>
							</a></li> -->
							<!-- <li class="dropdown-header">Options</li>
							<li><a href="#">View all messages <span
									class="pull-right"><i class="fa fa-arrow-right"></i></span>
							</a></li>
							<li><a href="#">Mark as read <span class="pull-right"><i
										class="fa fa-arrow-right"></i></span> -->
							</a></li>
						</ul> <!--end .dropdown-menu --></li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ANONYMOUS')">
					<li><a href="${contextPath}/login"
						class=".navbar-btn btn ink-reaction btn-flat btn-success"
						style="text-transform: none;"> Log In </a></li>
					<li><a href="${contextPath}/users/signup"
						class=".navbar-btn codespot-btn-signup btn btn-block ink-reaction btn-raised btn-success"
						style="text-transform: none;"> Sign Up </a></li>
				</security:authorize>
			</ul>

			<security:authorize access="hasRole('ROLE_USER')">
				<ul class="header-nav header-nav-profile">
					<li class="dropdown"><a href="javascript:void(0);"
						class="dropdown-toggle ink-reaction" data-toggle="dropdown"> <img
							src="${contextPath}/resources/img/avtars/a (1).png" alt="" /> <span
							class="profile-info"> <security:authentication
									property="principal.username" /> <small>Administrator</small>
						</span>
					</a>
						<ul class="dropdown-menu animation-dock">
							<li><a href="#">My profile</a></li>
							<li><a href="${contextPath}/searchusers"><i
									class="fa fa-users" area-hidden="true"></i> Search Users </a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-fw fa-lock"></i> Lock</a></li>
							<li><a href="${contextPath}/logout"><i
									class="fa fa-fw fa-power-off text-danger"></i> Logout</a></li>
						</ul></li>
				</ul>
			</security:authorize>

			<security:authorize access="hasRole('ROLE_USER')" var="isUser">
				<ul class="header-nav header-nav-toggle">
					<li><a class="btn btn-icon-toggle btn-default"
						href="#offcanvas-search" data-toggle="offcanvas"
						data-backdrop="false"> <i class="fa fa-comment"></i>
					</a></li>
				</ul>
			</security:authorize>
		</div>
	</div>
	
</header>