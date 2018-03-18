<<<<<<< HEAD
<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/img/icons/codespot.ico">
	    <title><spring:message code="app.name" /></title>


		<!-- BEGIN META -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="keywords" content="your,keywords">
		<meta name="description" content="Short explanation about this website">
		<!-- END META -->

		<!-- BEGIN STYLESHEETS -->
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/bootstrap-datepicker/datepicker3.css?1424887858" />
		<link href='http://fonts.googleapis.com/css?family=Roboto:300italic,400italic,300,400,500,700,900' rel='stylesheet' type='text/css'/>
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/bootstrap.css?1422792965" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/materialadmin.css?1425466319" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/font-awesome.min.css?1422529194" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/material-design-iconic-font.min.css?1421434286" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/bootstrap-multiselect/bootstrap-multiselect.css?1419109895" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/animsition/css/animsition.min.css" />
		<!-- END STYLESHEETS -->
		
<style type="text/css">
.header-nav>li>a.codespot-btn-signup:hover, .header-nav>li>a.codespot-btn-signup:focus
	{
	text-decoration: none;
	background-color: #009935;
}

.btn-xs {
    font-size: 10px;
}

.searchBarOn {
/*  	outline: -webkit-focus-ring-color auto 2px; */
 	outline: solid #009829 2px;
	-webkit-transition: width 3s ease-in-out;
	-moz-transition: width 3s ease-in-out;
	-o-transition: width 3s ease-in-out;
	-ms-transition: width 3s ease-in-out;
	transition: width 3s ease-in-out;
	transition-timing-function: ease-in-out;

	width: 93%;
}
</style>		
	</head>
	<body class="menubar-hoverable header-fixed ">


		<!-- BEGIN HEADER-->
		<jsp:include page="contents/header.jsp"/>
		<!-- END HEADER-->

		<!-- BEGIN BASE-->
		<div id="base">

			<!-- BEGIN OFFCANVAS LEFT -->
			<div class="offcanvas">
			</div>
			<!-- END OFFCANVAS LEFT -->

			<!-- BEGIN CONTENT-->
			<div id="content">
				<section>
				<div class="section-body contain-lg">
					<div class="row">
						<div class="col-sm-9">
							<div class="card card-underline">
								<div class="card-head">
									<ul class="nav nav-tabs pull-right" data-toggle="tabs">
										<li class="active"><a href="#web1">newest</a></li>
										<li><a href="#web2">featured</a></li>
										<li><a href="#web3">frequent</a></li>
										<li><a href="#web4">votes</a></li>
										<li><a href="#web5">active</a></li>
									</ul>
									<header>Top Questions</header>
								</div>
								<div class="card-body tab-content animsition">
									<div class="tab-pane active" id="web1">
										<div class="row">
											<div class="">
												<!-- BEGIN PAGE HEADER -->
												<div class="margin-bottom-xxl">
													<span class="text-light text-lg"><strong>${totalElm}</strong> records found</span>
													<div class="disabled btn-group btn-group-sm pull-right">
														<button type="button"
															class="btn btn-default-light dropdown-toggle"
															data-toggle="dropdown">
															<span class="glyphicon glyphicon-arrow-down"></span> Sort
														</button>
														<ul
															class="dropdown-menu dropdown-menu-right animation-dock"
															role="menu">
															<li class="disabled active"><a href="#">Newest</a></li>
															<li class="disabled"><a href="#">Oldest</a></li>
															<li class="disabled"><a href="#">Popularity</a></li>
															<li class="disabled"><a href="#">Titles</a></li>
														</ul>
													</div>
												</div>
												<!--end .margin-bottom-xxl -->
												<!-- END PAGE HEADER -->

												<!-- BEGIN RESULT LIST -->
												<div class="list-results list-results-underlined">
													<c:forEach items="${questionList}" var="questionItr">
														<div class="col-xs-12" style="padding-left:10px; padding-right:10px;">
															<div class="col-xs-8">
																<p class="" style="margin-bottom: -2px;">
																	<a class="text-medium text-lg text-primary"
																		href="${contextPath}/questions/${questionItr.questionId}">${questionItr.questionTitle}</a>
																</p>
																<div class="contain-xs pull-left text-default-light">
																	${questionItr.questionShortDescription}
																	<div class="list-tags fa-sm"
																		style="margin-top: 13px; margin-bottom: -18px;">
																		<a class="btn btn-xs btn-default">java</a> <a
																			class="btn btn-xs btn-default">Spring</a> <a
																			class="btn btn-xs btn-default">CSS3</a> <a
																			class="btn btn-xs btn-default">jQuery</a>
																	</div>
																</div>
															</div>
															<div class="col-xs-4">
																<p>
																	 <span class="pull-right createTimestamps" timestamp="${questionItr.createTimestamp}">${questionItr.createTimestamp}</span>
																	 <br /><a class="pull-right" style="color:#5c489c;" href="../../html/mail/inbox.html">${questionItr.user.userName}</a>
																</p>
															</div>
														</div>
													</c:forEach>
												</div>
												<!--end .list-results -->
												<!-- END RESULTS LIST -->

												<!-- BEGIN PAGING -->
												<div class="row">
													<div class="col-lg-12 text-center">

														<c:url var="firstUrl" value="/questions?pageNo=1" />
														<c:url var="lastUrl" value="/questions?pageNo=${totalPages}" />
														<c:url var="prevUrl" value="/questions?pageNo=${currentIndex - 1}" />
														<c:url var="nextUrl" value="/questions?pageNo=${currentIndex + 1}" />

														<ul class="pagination">
															<c:choose>
																<c:when test="${currentIndex == 1}">
																	<li class="disabled"><a href="#">&lt;&lt;</a></li>
																	<li class="disabled"><a href="#">&lt;</a></li>
																</c:when>
																<c:otherwise>
																	<li><a href="${firstUrl}">&lt;&lt;</a></li>
																	<li><a href="${prevUrl}">&lt;</a></li>
																</c:otherwise>
															</c:choose>
															<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
																<c:url var="pageUrl" value="/questions?pageNo=${i}" />
																<c:choose>
																	<c:when test="${i == currentIndex}">
																		<li class="active"><a href="${pageUrl}"><c:out
																					value="${i}" /></a></li>
																	</c:when>
																	<c:otherwise>
																		<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
															<c:choose>
																<c:when test="${currentIndex == totalPages}">
																	<li class="disabled"><a href="#">&gt;</a></li>
																	<li class="disabled"><a href="#">&gt;&gt;</a></li>
																</c:when>
																<c:otherwise>
																	<li><a href="${nextUrl}">&gt;</a></li>
																	<li><a href="${lastUrl}">&gt;&gt;</a></li>
																</c:otherwise>
															</c:choose>
														</ul>
													</div>
												</div>
												<!-- END PAGING -->

											</div>
										</div>
									</div>
									<div class="tab-pane" id="web2">
										<p>
											<img class="img-responsive"
												src=""
												alt="">
										</p>
									</div>
									<div class="tab-pane" id="web3">
										<p>
											<img class="img-responsive"
												src=""
												alt="">
										</p>
									</div>
									<div class="tab-pane" id="web4">
										<p>
											<img class="img-responsive"
												src=""
												alt="">
										</p>
									</div>
									<div class="tab-pane" id="web5">
										<p>
											<img class="img-responsive"
												src=""
												alt="">
										</p>
									</div>
								</div>
								<!-- BEGIN BLOG MENUBAR -->
								<!--end .col -->
							</div>
						</div>

						<!-- BEGIN BLOG MENUBAR -->
						<div class="col-sm-3">
							<p><a  href="${contextPath}/questions/ask" class="btn btn-block button-success ink-reaction btn-success" style="text-transform: full-width;"><i class="fa fa-edit"></i> Ask Question</a></p>
							<div class="card-body style-default-light">
								<h3 class="text-light">About</h3>
								<p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
								<h3 class="text-light">Archives</h3>
								<ul class="nav nav-pills nav-stacked nav-transparent">
									<li><a href="#"><span class="badge pull-right">42</span>Design</a></li>
									<li><a href="#"><span class="badge pull-right">56</span>Javascript</a></li>
									<li><a href="#"><span class="badge pull-right">32</span>Java</a></li>
									<li><a href="#"><span class="badge pull-right">19</span>C#</a></li>
									<li><a href="#"><span class="badge pull-right">22</span>Php</a></li>
									<li><a href="#"><span class="badge pull-right">14</span>Android</a></li>
									<li><a href="#"><span class="badge pull-right">5</span>ruby-on-rails</a></li>
								</ul>
								<h3 class="text-light">Elsewhere</h3>
								<ul class="nav nav-pills nav-stacked nav-transparent">
									<li><a href="#">GitHub</a></li>
									<li><a href="#">Twitter</a></li>
									<li><a href="#">Facebook</a></li>
								</ul>
							</div><!-- .card-body -->
						</div><!--end .col -->
					</div>
					<!--end .card -->
					<!-- <em class="text-caption">Right aligned tabs</em> -->
				</div>

				<!--end .section-body -->
				</section>
			</div><!--end #content-->
			<!-- END CONTENT -->

			<!-- BEGIN OFFCANVAS RIGHT -->
				<jsp:include page="contents/ChatUserList.jsp"/>
			<!-- END OFFCANVAS RIGHT -->

		</div><!--end #base-->
		<!-- END BASE -->

		<!-- Footer -->
			<jsp:include page="contents/footer.jsp"/>
		<!-- Footer -->
		

		<!-- BEGIN JAVASCRIPT -->
		<script src="${contextPath}/resources/js/libs/jquery/jquery-1.11.2.min.js"></script>
		<script src="${contextPath}/resources/js/libs/jquery/jquery-migrate-1.2.1.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap/bootstrap.min.js"></script>
		<script src="${contextPath}/resources/js/libs/spin.js/spin.min.js"></script>
		<script src="${contextPath}/resources/js/libs/autosize/jquery.autosize.min.js"></script>
		<script src="${contextPath}/resources/js/core/moment.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-multiselect/bootstrap-multiselect.js"></script>
		<script src="${contextPath}/resources/js/libs/nanoscroller/jquery.nanoscroller.min.js"></script>
		<script src="${contextPath}/resources/animsition/js/animsition.min.js"></script>
		<script src="${contextPath}/resources/js/core/source/App.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavigation.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppOffcanvas.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppCard.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppForm.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavSearch.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppVendor.js"></script>
		<script src="${contextPath}/resources/js/core/demo/Demo.js"></script>
		<script src="${contextPath}/resources/js/core/demo/DemoPageSearch.js"></script>
		<!-- END JAVASCRIPT -->

<script type="text/javascript">

function codespotsearchFocusIn() {
	$("#codespotsearch").addClass("searchBarOn");
}


$(".createTimestamps").each(function(){
	var timestamp = $(this).attr('timestamp');
	var d = moment(timestamp);
	$(this).html('asked '+moment(d, "YYYYMMDD h:mm:ss a").fromNow());
});

</script>
<script type="text/javascript">
$( document ).ready(function() {
	  "use strict";
	  $(".animsition").animsition({
	    inClass: 'fade-in',
	    outClass: 'fade-out',
	    inDuration: 1500,
	    outDuration: 800,
	    linkElement: '.a-link',
	    // e.g. linkElement: 'a:not([target="_blank"]):not([href^="#"])'
	    loading: true,
	    loadingParentElement: 'body', //animsition wrapper element
	    loadingClass: 'animsition-loading',
	    loadingInner: '', // e.g '<img src="loading.svg" />'
	    timeout: false,
	    timeoutCountdown: 5000,
	    onLoadEvent: true,
	    browser: [ 'animation-duration', '-webkit-animation-duration'],
	    overlay : false,
	    overlayClass : 'animsition-overlay-slide',
	    overlayParentElement : 'body',
	    transition: function(url){ window.location.href = url; }
	  });
});
</script>
	</body>
</html>
=======
=======
>>>>>>> post-chat
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/img/icons/codespot.ico">
	    <title><spring:message code="app.name" /></title>


		<!-- BEGIN META -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="keywords" content="your,keywords">
		<meta name="description" content="Short explanation about this website">
		<!-- END META -->

		<!-- BEGIN STYLESHEETS -->
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/bootstrap-datepicker/datepicker3.css?1424887858" />
		<link href='http://fonts.googleapis.com/css?family=Roboto:300italic,400italic,300,400,500,700,900' rel='stylesheet' type='text/css'/>
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/bootstrap.css?1422792965" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/materialadmin.css?1425466319" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/font-awesome.min.css?1422529194" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/material-design-iconic-font.min.css?1421434286" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/bootstrap-multiselect/bootstrap-multiselect.css?1419109895" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/animsition/css/animsition.min.css" />
		<!-- END STYLESHEETS -->
		
<style type="text/css">
.header-nav>li>a.codespot-btn-signup:hover, .header-nav>li>a.codespot-btn-signup:focus
	{
	text-decoration: none;
	background-color: #009935;
}

.btn-xs {
    font-size: 10px;
}

.searchBarOn {
/*  	outline: -webkit-focus-ring-color auto 2px; */
 	outline: solid #009829 2px;
	-webkit-transition: width 3s ease-in-out;
	-moz-transition: width 3s ease-in-out;
	-o-transition: width 3s ease-in-out;
	-ms-transition: width 3s ease-in-out;
	transition: width 3s ease-in-out;
	transition-timing-function: ease-in-out;

	width: 93%;
}
</style>		
	</head>
	<body class="menubar-hoverable header-fixed ">


		<!-- BEGIN HEADER-->
		<jsp:include page="contents/header.jsp"/>
		<!-- END HEADER-->

		<!-- BEGIN BASE-->
		<div id="base">

			<!-- BEGIN OFFCANVAS LEFT -->
			<div class="offcanvas">
			</div>
			<!-- END OFFCANVAS LEFT -->

			<!-- BEGIN CONTENT-->
			<div id="content">
				<section>
				<div class="section-body contain-lg">
					<div class="row">
						<div class="col-sm-9">
							<div class="card card-underline">
								<div class="card-head">
									<ul class="nav nav-tabs pull-right" data-toggle="tabs">
										<li class="active"><a href="#web1">newest</a></li>
										<li><a href="#web2">featured</a></li>
										<li><a href="#web3">frequent</a></li>
										<li><a href="#web4">votes</a></li>
										<li><a href="#web5">active</a></li>
									</ul>
									<header>Top Questions</header>
								</div>
								<div class="card-body tab-content animsition">
									<div class="tab-pane active" id="web1">
										<div class="row">
											<div class="">
												<!-- BEGIN PAGE HEADER -->
												<div class="margin-bottom-xxl">
													<span class="text-light text-lg"><strong>${totalElm}</strong> records found</span>
													<div class="disabled btn-group btn-group-sm pull-right">
														<button type="button"
															class="btn btn-default-light dropdown-toggle"
															data-toggle="dropdown">
															<span class="glyphicon glyphicon-arrow-down"></span> Sort
														</button>
														<ul
															class="dropdown-menu dropdown-menu-right animation-dock"
															role="menu">
															<li class="disabled active"><a href="#">Newest</a></li>
															<li class="disabled"><a href="#">Oldest</a></li>
															<li class="disabled"><a href="#">Popularity</a></li>
															<li class="disabled"><a href="#">Titles</a></li>
														</ul>
													</div>
												</div>
												<!--end .margin-bottom-xxl -->
												<!-- END PAGE HEADER -->

												<!-- BEGIN RESULT LIST -->
												<div class="list-results list-results-underlined">
													<c:forEach items="${questionList}" var="questionItr">
														<div class="col-xs-12" style="padding-left:10px; padding-right:10px;">
															<div class="col-xs-8">
																<p class="" style="margin-bottom: -2px;">
																	<a class="text-medium text-lg text-primary"
																		href="${contextPath}/questions/${questionItr.questionId}">${questionItr.questionTitle}</a>
																</p>
																<div class="contain-xs pull-left text-default-light">
																	${questionItr.questionShortDescription}
																	<div class="list-tags fa-sm"
																		style="margin-top: 13px; margin-bottom: -18px;">
																		<a class="btn btn-xs btn-default">java</a> <a
																			class="btn btn-xs btn-default">Spring</a> <a
																			class="btn btn-xs btn-default">CSS3</a> <a
																			class="btn btn-xs btn-default">jQuery</a>
																	</div>
																</div>
															</div>
															<div class="col-xs-4">
																<p>
																	 <span class="pull-right createTimestamps" timestamp="${questionItr.createTimestamp}">${questionItr.createTimestamp}</span>
																	 <br /><a class="pull-right" style="color:#5c489c;" href="../../html/mail/inbox.html">${questionItr.user.userName}</a>
																</p>
															</div>
														</div>
													</c:forEach>
												</div>
												<!--end .list-results -->
												<!-- END RESULTS LIST -->

												<!-- BEGIN PAGING -->
												<div class="row">
													<div class="col-lg-12 text-center">

														<c:url var="firstUrl" value="/questions?pageNo=1" />
														<c:url var="lastUrl" value="/questions?pageNo=${totalPages}" />
														<c:url var="prevUrl" value="/questions?pageNo=${currentIndex - 1}" />
														<c:url var="nextUrl" value="/questions?pageNo=${currentIndex + 1}" />

														<ul class="pagination">
															<c:choose>
																<c:when test="${currentIndex == 1}">
																	<li class="disabled"><a href="#">&lt;&lt;</a></li>
																	<li class="disabled"><a href="#">&lt;</a></li>
																</c:when>
																<c:otherwise>
																	<li><a href="${firstUrl}">&lt;&lt;</a></li>
																	<li><a href="${prevUrl}">&lt;</a></li>
																</c:otherwise>
															</c:choose>
															<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
																<c:url var="pageUrl" value="/questions?pageNo=${i}" />
																<c:choose>
																	<c:when test="${i == currentIndex}">
																		<li class="active"><a href="${pageUrl}"><c:out
																					value="${i}" /></a></li>
																	</c:when>
																	<c:otherwise>
																		<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
															<c:choose>
																<c:when test="${currentIndex == totalPages}">
																	<li class="disabled"><a href="#">&gt;</a></li>
																	<li class="disabled"><a href="#">&gt;&gt;</a></li>
																</c:when>
																<c:otherwise>
																	<li><a href="${nextUrl}">&gt;</a></li>
																	<li><a href="${lastUrl}">&gt;&gt;</a></li>
																</c:otherwise>
															</c:choose>
														</ul>
													</div>
												</div>
												<!-- END PAGING -->

											</div>
										</div>
									</div>
									<div class="tab-pane" id="web2">
										<p>
											<img class="img-responsive"
												src=""
												alt="">
										</p>
									</div>
									<div class="tab-pane" id="web3">
										<p>
											<img class="img-responsive"
												src=""
												alt="">
										</p>
									</div>
									<div class="tab-pane" id="web4">
										<p>
											<img class="img-responsive"
												src=""
												alt="">
										</p>
									</div>
									<div class="tab-pane" id="web5">
										<p>
											<img class="img-responsive"
												src=""
												alt="">
										</p>
									</div>
								</div>
								<!-- BEGIN BLOG MENUBAR -->
								<!--end .col -->
							</div>
						</div>

						<!-- BEGIN BLOG MENUBAR -->
						<div class="col-sm-3">
							<p><a  href="${contextPath}/questions/ask" class="btn btn-block button-success ink-reaction btn-success" style="text-transform: full-width;"><i class="fa fa-edit"></i> Ask Question</a></p>
							<div class="card-body style-default-light">
								<h3 class="text-light">About</h3>
								<p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
								<h3 class="text-light">Archives</h3>
								<ul class="nav nav-pills nav-stacked nav-transparent">
									<li><a href="#"><span class="badge pull-right">42</span>Design</a></li>
									<li><a href="#"><span class="badge pull-right">56</span>Javascript</a></li>
									<li><a href="#"><span class="badge pull-right">32</span>Java</a></li>
									<li><a href="#"><span class="badge pull-right">19</span>C#</a></li>
									<li><a href="#"><span class="badge pull-right">22</span>Php</a></li>
									<li><a href="#"><span class="badge pull-right">14</span>Android</a></li>
									<li><a href="#"><span class="badge pull-right">5</span>ruby-on-rails</a></li>
								</ul>
								<h3 class="text-light">Elsewhere</h3>
								<ul class="nav nav-pills nav-stacked nav-transparent">
									<li><a href="#">GitHub</a></li>
									<li><a href="#">Twitter</a></li>
									<li><a href="#">Facebook</a></li>
								</ul>
							</div><!-- .card-body -->
						</div><!--end .col -->
					</div>
					<!--end .card -->
					<!-- <em class="text-caption">Right aligned tabs</em> -->
				</div>

				<!--end .section-body -->
				</section>
			</div><!--end #content-->
			<!-- END CONTENT -->

			<!-- BEGIN OFFCANVAS RIGHT -->
				<jsp:include page="contents/ChatUserList.jsp"/>
			<!-- END OFFCANVAS RIGHT -->

		</div><!--end #base-->
		<!-- END BASE -->

		<!-- Footer -->
			<jsp:include page="contents/footer.jsp"/>
		<!-- Footer -->
		

		<!-- BEGIN JAVASCRIPT -->
		<script src="${contextPath}/resources/js/libs/jquery/jquery-1.11.2.min.js"></script>
		<script src="${contextPath}/resources/js/libs/jquery/jquery-migrate-1.2.1.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap/bootstrap.min.js"></script>
		<script src="${contextPath}/resources/js/libs/spin.js/spin.min.js"></script>
		<script src="${contextPath}/resources/js/libs/autosize/jquery.autosize.min.js"></script>
		<script src="${contextPath}/resources/js/core/moment.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-multiselect/bootstrap-multiselect.js"></script>
		<script src="${contextPath}/resources/js/libs/nanoscroller/jquery.nanoscroller.min.js"></script>
		<script src="${contextPath}/resources/animsition/js/animsition.min.js"></script>
		<script src="${contextPath}/resources/js/core/source/App.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavigation.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppOffcanvas.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppCard.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppForm.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavSearch.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppVendor.js"></script>
		<script src="${contextPath}/resources/js/core/demo/Demo.js"></script>
		<script src="${contextPath}/resources/js/core/demo/DemoPageSearch.js"></script>
		<!-- END JAVASCRIPT -->

<script type="text/javascript">

function codespotsearchFocusIn() {
	$("#codespotsearch").addClass("searchBarOn");
}


$(".createTimestamps").each(function(){
	var timestamp = $(this).attr('timestamp');
	var d = moment(timestamp);
	$(this).html('asked '+moment(d, "YYYYMMDD h:mm:ss a").fromNow());
});

</script>
<script type="text/javascript">
$( document ).ready(function() {
	  "use strict";
	  $(".animsition").animsition({
	    inClass: 'fade-in',
	    outClass: 'fade-out',
	    inDuration: 1500,
	    outDuration: 800,
	    linkElement: '.a-link',
	    // e.g. linkElement: 'a:not([target="_blank"]):not([href^="#"])'
	    loading: true,
	    loadingParentElement: 'body', //animsition wrapper element
	    loadingClass: 'animsition-loading',
	    loadingInner: '', // e.g '<img src="loading.svg" />'
	    timeout: false,
	    timeoutCountdown: 5000,
	    onLoadEvent: true,
	    browser: [ 'animation-duration', '-webkit-animation-duration'],
	    overlay : false,
	    overlayClass : 'animsition-overlay-slide',
	    overlayParentElement : 'body',
	    transition: function(url){ window.location.href = url; }
	  });
});
</script>
	</body>
</html>
>>>>>>> post-chat
