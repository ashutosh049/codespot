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
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="keywords" content="your,keywords">
		<meta name="description" content="Short explanation about this website">
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/bootstrap-datepicker/datepicker3.css?1424887858" />
		<link href='http://fonts.googleapis.com/css?family=Roboto:300italic,400italic,300,400,500,700,900' rel='stylesheet' type='text/css'/>
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/bootstrap.css?1422792965" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/materialadmin.css?1425466319" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/font-awesome.min.css?1422529194" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/material-design-iconic-font.min.css?1421434286" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/bootstrap-multiselect/bootstrap-multiselect.css?1419109895" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/animsition/css/animsition.min.css" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-default/libs/toastr/toastr.css?1425466569" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/chatbox.css"/>
</head>
<body class="menubar-hoverable header-fixed " onload="loadAllContatcs('${contextPath}/searchusers/AllAddable');">
	<jsp:include page="contents/header.jsp" />
	<div id="base">
		<div class="offcanvas"></div>
		<div id="content">
			<section>
				<div class="section-body contain-lg">
					<div class="row">
						<div class="col-lg-6 text-center input-group" style="margin-bottom: 35px;">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-search"></i></span>
								<input type="text" class="form-control"
									placeholder="You're searching for..."> <span
									class="input-group-btn"><button class="btn btn-primary"
										type="submit">Find</button></span>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="card tabs-left style-default-light animsition"
								id="contact-card-pane">
								<ul class="card-head nav nav-tabs col-lg-2" data-toggle="tabs">
									<li class="active"><a href="#allcontacts"
										class="btn btn-block  btn-flat btn-default "
										onclick="loadAllContatcs('${contextPath}/searchusers/AllAddable');">
											People you may know </a></li>
									<li class=""><a href="#sentfr"
										class="btn btn-block  btn-flat btn-default "
										onclick="loadAllAllAddableSent('${contextPath}/searchusers/AllAddableSent');">
											Sent </a></li>
									<li class=""><a href="#freinds"
										class="btn btn-block  btn-flat btn-default "
										onclick="loadAllAdded('${contextPath}/searchusers/AllAdded');">
											Friends </a></li>
								</ul>

								<div class="card-body tab-content style-default-bright">

									<!-- <div class="col-lg-12 text-center input-group"
										style="margin-bottom: 35px;">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="fa fa-search"></i></span> <input type="text"
												class="form-control" placeholder="You're searching for...">
											<span class="input-group-btn"><button
													class="btn btn-primary" type="submit">Find</button></span>
										</div>
									</div> -->

									<div class="tab-pane active" id="allcontacts">
										<div class="col-sm-8 col-md-8 col-lg-11" id="AllAddable">

										</div>
									</div>
									<div class="tab-pane" id="sentfr">
										<div class="col-sm-8 col-md-8 col-lg-11" id="AllAddableSent">

										</div>
									</div>
									<div class="tab-pane" id="freinds">
										<div class="col-sm-8 col-md-8 col-lg-11" id="AllAdded">

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
		</div>
		</section>
	</div>
	<jsp:include page="contents/ChatUserList.jsp" />
	</div>
	<jsp:include page="contents/chatbox.jsp" />
	<jsp:include page="contents/footer.jsp" />
<%-- 	<img id="page-loading" src="${contextPath}/resources/img/loading/loading.gif" alt="loading" /> --%>


	<script src="${contextPath}/resources/js/libs/jquery/jquery-1.11.2.min.js"></script>
	<script src="${contextPath}/resources/js/libs/jquery/jquery-migrate-1.2.1.min.js"></script>
	<script src="${contextPath}/resources/js/libs/jquery-ui/jquery-ui.min.js"></script>
	<script src="${contextPath}/resources/js/libs/bootstrap/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/libs/spin.js/spin.min.js"></script>
	<script src="${contextPath}/resources/js/libs/autosize/jquery.autosize.min.js"></script>
	<script src="${contextPath}/resources/js/core/moment.min.js"></script>
	<script src="${contextPath}/resources/js/libs/bootstrap-multiselect/bootstrap-multiselect.js"></script>
	<script src="${contextPath}/resources/js/libs/nanoscroller/jquery.nanoscroller.min.js"></script>
	<script src="${contextPath}/resources/js/libs/toastr/toastr.js"></script>
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
	<script src="${contextPath}/resources/js/core/demo/DemoUIMessages.js"></script>
	<script src="${contextPath}/resources/js/core/demo/DemoPageContacts.js"></script>
	<script src="${contextPath}/resources/js/libs/moment/moment.min.js"></script>
	<script src="${contextPath}/resources/js/libs/bootstrap-rating/bootstrap-rating-input.min.js"></script>
	<script src="${contextPath}/resources/js/libs/microtemplating/microtemplating.min.js"></script>
	<script src="${contextPath}/resources/js/libs/summernote/summernote.min.js"></script>
	<script src="${contextPath}/resources/js/core/sockjs.min.js"></script>
	<script src="${contextPath}/resources/js/core/stomp.min.js"></script>
	<script src="${contextPath}/resources/js/core/search-users.js"></script>


</body>
<script type="text/javascript">var contextPath = "<%=request.getContextPath()%>";</script>
</html>
