<<<<<<< HEAD
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
		<title>Material Admin - Compose mail</title>

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
		
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/summernote/summernote.css?1425218701" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/js/libs/google-code-prettify/prettify.css" />
		
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/select2/select2.css?1424887856" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/multi-select/multi-select.css?1424887857" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/jquery-ui/jquery-ui-theme.css?1423393666" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/bootstrap-tagsinput/bootstrap-tagsinput.css?1424887862" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/typeahead/typeahead.css?1424887863" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/animsition/css/animsition.min.css" >
		<!-- END STYLESHEETS -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script type="text/javascript" src="${contextPath}/resources/js/libs/utils/html5shiv.js?1403934957"></script>
		<script type="text/javascript" src="${contextPath}/resources/js/libs/utils/respond.min.js?1403934956"></script>
		<![endif]-->
	</head>
<!-- 	<body class="header-fixed " onload="PR.prettyPrint()"> -->
	<body class="header-fixed">
		<!-- BEGIN HEADER-->
		<jsp:include page="contents/header.jsp"/>
		<!-- END HEADER-->

		<!-- BEGIN BASE-->
		<div id="base">

			<!-- BEGIN OFFCANVAS LEFT -->
			<div class="offcanvas">
			</div><!--end .offcanvas-->
			<!-- END OFFCANVAS LEFT -->

			<!-- BEGIN CONTENT-->
			<div id="content">
				<section>
					<!-- <div class="section-header">
						<ol class="breadcrumb">
							<li><a href="../../../html/pages/contacts/search.html">Contacts</a></li>
							<li class="active">Add contact</li>
						</ol>
					</div> -->
					<div class="section-body contain-lg">
						<div class="row" >

							<!-- BEGIN ADD CONTACTS FORM -->
							<div class="col-md-12">
								<div class="card" id="editCard">
									<div class="card-head style-success">
										<header><i class="fa fa-edit"></i> Ask a new question</header>
									</div>
									<%-- <form:form id="questionAskForm" class="form" role="form" modelAttribute="question" > --%>
									<form:form id="questionAskForm" class="form" role="form" modelAttribute="question" action="${contextPath}/questions/create">
										<div class="card-body animsition"><!-- form-inverse -->
											<div class="row">
												<div class="col-md-8">
													
													<div class="row">
														<div class="col-md-10">
															<div class="form-group">
																<form:input type="text" path="questionTitle" class="form-control" id="title" name="title" placeholder="What's your programming question ? Be specific.."></form:input>
																<label for="title" class="primary-dark">Title</label>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-10">
															<div class="form-group">
																<form:textarea type="text" path="questionShortDescription" class="form-control" id="title" name="title" placeholder="Short description to be appeared on listing..." maxlength="120"></form:textarea>
																<label for="title" class="primary-dark">Short Description</label>
															</div>
																<p class="form-text text-muted has-warning">
																  Max 120 characters
																</p>
														</div>
													</div>
													
												</div>
												
												<div class="col-md-4">
													<div class="row">
														<div class="col-md-10">
															<div class="form-group">
																<form:select path="questionTags" class="form-control select2-list" data-placeholder="Select tags" multiple="multiple">
																	<option value="java">Java</option>
																	<option value="c">c</option>
																	<option value="cplusplus">c++</option>
																	<option value="python">python</option>
																	<option value="ruby">ruby</option>
																	<option value="mysql">MySQL</option>
																	<option value="tomcat">Tomcat</option>
																	<option value="iOS">iOS</option>
																	<option value="javascript">Javascript</option>
																	<option value="angular">Angular</option>
																	<option value="nodejs">Node.js</option>
																	<option value="xxxxxx">xxxxx</option>
																	
																</form:select>
																<label>Tags</label>
															</div>
														</div><!--end .col -->
													</div>
												</div>
											</div><!--end .row -->
										</div><!--end .card-body -->

										<div class="card-body tab-content">
												<div class="form-group">
												<form:textarea path="questionDescription" id="description" name="description" class="form-control control-4-rows" spellcheck="false"></form:textarea>
												<form:input type="hidden" path="questionShortDescription" id="questionShortDescription" name="questionShortDescription"></form:input>
												<!-- <div id="summernote1">
													<h2>WYSIWYG Editor</h2>
													Lorem ipsum dolor sit amet, consectetur adipiscing elit.
													Aliquam ullamcorper sapien non nisl facilisis bibendum in
													quis tellus. Duis in urna bibendum turpis pretium
													fringilla. Aenean neque velit, porta eget mattis ac,
													imperdiet quis nisi. Donec non dui et tortor vulputate
													luctus. Praesent consequat rhoncus velit, ut molestie arcu
													venenatis sodales.
													<h3>Lacinia</h3>
													<ul>
														<li>Suspendisse tincidunt urna ut velit ullamcorper
															fermentum.</li>
														<li>Nullam mattis sodales lacus, in gravida sem
															auctor at.</li>
														<li>Praesent non lacinia mi.</li>
														<li>Mauris a ante neque.</li>
														<li>Aenean ut magna lobortis nunc feugiat sagittis.</li>
													</ul>
													<h3>Pellentesque adipiscing</h3>
													Maecenas quis ante ante. Nunc adipiscing rhoncus rutrum.
													Pellentesque adipiscing urna mi, ut tempus lacus ultrices
													ac. Pellentesque sodales, libero et mollis interdum, dui
													odio vestibulum dolor, eu pellentesque nisl nibh quis nunc.
													Sed porttitor leo adipiscing venenatis vehicula. Aenean
													quis viverra enim. Praesent porttitor ut ipsum id ornare.
												</div> -->
											</div>
											
										</div><!--end .card-body.tab-content -->
										<!-- END FORM TAB PANES -->

										<!-- BEGIN FORM FOOTER -->
										<div class="card-actionbar">
											<div class="card-actionbar-row">
												<a class="btn btn-flat" href="../../../html/pages/contacts/search.html">CANCEL</a>
												<button type="submit" class="btn ink-reaction btn-raised btn-primary">Post your question</button>
											</div><!--end .card-actionbar-row -->
										</div><!--end .card-actionbar -->
										<!-- END FORM FOOTER -->

									</form:form>
								</div><!--end .card -->
							</div><!--end .col -->
							<!-- END ADD CONTACTS FORM -->

						</div><!--end .row -->
					</div><!--end .section-body -->
				</section>
			</div><!--end #content-->
			<!-- END CONTENT -->

			<!-- BEGIN MENUBAR-->
			<!--end #menubar-->
			<!-- END MENUBAR -->

			<!-- BEGIN OFFCANVAS RIGHT -->
			<div class="offcanvas">

				<!-- BEGIN OFFCANVAS SEARCH -->
				<div id="offcanvas-search" class="offcanvas-pane width-8">
					<div class="offcanvas-head">
						<header class="text-primary">Search</header>
						<div class="offcanvas-tools">
							<a class="btn btn-icon-toggle btn-default-light pull-right" data-dismiss="offcanvas">
								<i class="md md-close"></i>
							</a>
						</div>
					</div>
					<div class="offcanvas-body no-padding">
						<ul class="list ">
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>A</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar4.jpg?1404026791" alt="" />
									</div>
									<div class="tile-text">
										Alex Nelson
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar9.jpg?1404026744" alt="" />
									</div>
									<div class="tile-text">
										Ann Laurens
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>J</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar2.jpg?1404026449" alt="" />
									</div>
									<div class="tile-text">
										Jessica Cruise
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar8.jpg?1404026729" alt="" />
									</div>
									<div class="tile-text">
										Jim Peters
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>M</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar5.jpg?1404026513" alt="" />
									</div>
									<div class="tile-text">
										Mabel Logan
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar11.jpg?1404026774" alt="" />
									</div>
									<div class="tile-text">
										Mary Peterson
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar3.jpg?1404026799" alt="" />
									</div>
									<div class="tile-text">
										Mike Alba
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>N</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar6.jpg?1404026572" alt="" />
									</div>
									<div class="tile-text">
										Nathan Peterson
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>P</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar7.jpg?1404026721" alt="" />
									</div>
									<div class="tile-text">
										Philip Ericsson
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>S</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar10.jpg?1404026762" alt="" />
									</div>
									<div class="tile-text">
										Samuel Parsons
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
						</ul>
					</div><!--end .offcanvas-body -->
				</div><!--end .offcanvas-pane -->
				<!-- END OFFCANVAS SEARCH -->

				<!-- BEGIN OFFCANVAS CHAT -->
				<div id="offcanvas-chat" class="offcanvas-pane style-default-light width-12">
					<div class="offcanvas-head style-default-bright">
						<header class="text-primary">Chat with Ann Laurens</header>
						<div class="offcanvas-tools">
							<a class="btn btn-icon-toggle btn-default-light pull-right" data-dismiss="offcanvas">
								<i class="md md-close"></i>
							</a>
							<a class="btn btn-icon-toggle btn-default-light pull-right" href="#offcanvas-search" data-toggle="offcanvas" data-backdrop="false">
								<i class="md md-arrow-back"></i>
							</a>
						</div>
						<form class="form">
							<div class="form-group floating-label">
								<textarea name="sidebarChatMessage" id="sidebarChatMessage" class="form-control autosize" rows="1"></textarea>
								<label for="sidebarChatMessage">Leave a message</label>
							</div>
						</form>
					</div>
					<div class="offcanvas-body">
						<ul class="list-chats">
							<li>
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar1.jpg?1403934956" alt="" /></div>
									<div class="chat-body">
										Yes, it is indeed very beautiful.
										<small>10:03 pm</small>
									</div>
								</div><!--end .chat -->
							</li>
							<li class="chat-left">
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar9.jpg?1404026744" alt="" /></div>
									<div class="chat-body">
										Did you see the changes?
										<small>10:02 pm</small>
									</div>
								</div><!--end .chat -->
							</li>
							<li>
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar1.jpg?1403934956" alt="" /></div>
									<div class="chat-body">
										I just arrived at work, it was quite busy.
										<small>06:44pm</small>
									</div>
									<div class="chat-body">
										I will take look in a minute.
										<small>06:45pm</small>
									</div>
								</div><!--end .chat -->
							</li>
							<li class="chat-left">
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar9.jpg?1404026744" alt="" /></div>
									<div class="chat-body">
										The colors are much better now.
									</div>
									<div class="chat-body">
										The colors are brighter than before.
										I have already sent an example.
										This will make it look sharper.
										<small>Mon</small>
									</div>
								</div><!--end .chat -->
							</li>
							<li>
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar1.jpg?1403934956" alt="" /></div>
									<div class="chat-body">
										Are the colors of the logo already adapted?
										<small>Last week</small>
									</div>
								</div><!--end .chat -->
							</li>
						</ul>
					</div><!--end .offcanvas-body -->
				</div><!--end .offcanvas-pane -->
				<!-- END OFFCANVAS CHAT -->

			</div><!--end .offcanvas-->
			<!-- END OFFCANVAS RIGHT -->

		</div><!--end #base-->
		<!-- END BASE -->

		<!-- BEGIN JAVASCRIPT -->

		<!-- BEGIN EXPERIENCE TEMPLATES -->
		
		<!-- END EXPERIENCE TEMPLATES -->

		<!-- BEGIN SKILLS TEMPLATES -->
		
		<!-- END SKILLS TEMPLATES -->

		<script src="${contextPath}/resources/js/libs/jquery/jquery-1.11.2.min.js"></script>
		<script src="${contextPath}/resources/js/libs/jquery/jquery-migrate-1.2.1.min.js"></script>
		<script src="${contextPath}/resources/js/libs/jquery-ui/jquery-ui.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap/bootstrap.min.js"></script>
		<script src="${contextPath}/resources/js/libs/spin.js/spin.min.js"></script>
		<script src="${contextPath}/resources/js/libs/autosize/jquery.autosize.min.js"></script>
		<script src="${contextPath}/resources/js/libs/inputmask/jquery.inputmask.bundle.min.js"></script>
		<script src="${contextPath}/resources/js/libs/moment/moment.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-multiselect/bootstrap-multiselect.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-rating/bootstrap-rating-input.min.js"></script>
		<script src="${contextPath}/resources/js/libs/nanoscroller/jquery.nanoscroller.min.js"></script>
		<script src="${contextPath}/resources/js/libs/microtemplating/microtemplating.min.js"></script>
		
		<script src="${contextPath}/resources/js/libs/summernote/summernote.min.js"></script>
		<script src="${contextPath}/resources/js/libs/summernote/summernote-ext-highlight.js"></script>
		<script src="${contextPath}/resources/js/libs/google-code-prettify/prettify.js"></script>
		
		<script src="${contextPath}/resources/js/libs/select2/select2.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
		<script src="${contextPath}/resources/js/libs/multi-select/jquery.multi-select.js"></script>
		<script src="${contextPath}/resources/js/libs/inputmask/jquery.inputmask.bundle.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js"></script>
		<script src="${contextPath}/resources/js/libs/typeahead/typeahead.bundle.min.js"></script>
		<script src="${contextPath}/resources/animsition/js/animsition.min.js"></script>
		<script src="${contextPath}/resources/js/core/source/App.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavigation.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppOffcanvas.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppCard.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppForm.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavSearch.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppVendor.js"></script>
		<script src="${contextPath}/resources/js/core/demo/Demo.js"></script>
		<script src="${contextPath}/resources/js/core/demo/DemoPageContacts.js"></script>
		<!-- END JAVASCRIPT -->

<script type="text/javascript">
$( document ).ready(function() {
	  "use strict";
	/* ------------------------------------- */
	/* Page Loading    ................... */
	/* ------------------------------------- */


	  $(".animsition").animsition({
	    inClass: 'fade-in',
	    outClass: 'fade-out',
	    inDuration: 1500,
	    outDuration: 800,
// 	    linkElement: '.a-link',
	    // e.g. linkElement: 'a:not([target="_blank"]):not([href^="#"])'
	    loading: true,
	    loadingParentElement: 'html', //animsition wrapper element
	    loadingClass: 'animsition-loading',
	    loadingInner: '', // e.g '<img src="loading.svg" />
	    timeout: false,
	    timeoutCountdown: 5000,
	    onLoadEvent: true,
	    browser: [ 'animation-duration', '-webkit-animation-duration'],
	    // "browser" option allows you to disable the "animsition" in case the css property in the array is not supported by your browser.
	    // The default setting is to disable the "animsition" in a browser that does not support "animation-duration".
	    overlay : false,
	    overlayClass : 'animsition-overlay-slide',
	    overlayParentElement : 'body',
	    transition: function(url){ window.location.href = url; }
	  });

	  /* -------------------------------------------------- */
		/* Update summernote question short-description     */
		/* ------------------------------------------------ */
	
	
	document.getElementById('questionAskForm').onsubmit = function(e) {
		var cleanText = $("#description").code();
		//var cleanText = code.replace(/<\/?[^>]+(>|$)/g, "");
		//cleanText = text.text();
		if (cleanText.length >= 200) {
			cleanText = cleanText.substring(0, 190);
		}
		cleanText = cleanText.replace(/<\/?[^>]+(>|$)/g, "");
		document.getElementById('questionShortDescription').value = cleanText;

	};

					});
</script>

	</body>
</html>
=======
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
		<title>Material Admin - Compose mail</title>

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
		
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/summernote/summernote.css?1425218701" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/js/libs/google-code-prettify/prettify.css" />
		
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/select2/select2.css?1424887856" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/multi-select/multi-select.css?1424887857" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/jquery-ui/jquery-ui-theme.css?1423393666" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/bootstrap-tagsinput/bootstrap-tagsinput.css?1424887862" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/typeahead/typeahead.css?1424887863" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/animsition/css/animsition.min.css" >
		<!-- END STYLESHEETS -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script type="text/javascript" src="${contextPath}/resources/js/libs/utils/html5shiv.js?1403934957"></script>
		<script type="text/javascript" src="${contextPath}/resources/js/libs/utils/respond.min.js?1403934956"></script>
		<![endif]-->
	</head>
<!-- 	<body class="header-fixed " onload="PR.prettyPrint()"> -->
	<body class="header-fixed">
		<!-- BEGIN HEADER-->
		<jsp:include page="contents/header.jsp"/>
		<!-- END HEADER-->

		<!-- BEGIN BASE-->
		<div id="base">

			<!-- BEGIN OFFCANVAS LEFT -->
			<div class="offcanvas">
			</div><!--end .offcanvas-->
			<!-- END OFFCANVAS LEFT -->

			<!-- BEGIN CONTENT-->
			<div id="content">
				<section>
					<!-- <div class="section-header">
						<ol class="breadcrumb">
							<li><a href="../../../html/pages/contacts/search.html">Contacts</a></li>
							<li class="active">Add contact</li>
						</ol>
					</div> -->
					<div class="section-body contain-lg">
						<div class="row" >

							<!-- BEGIN ADD CONTACTS FORM -->
							<div class="col-md-12">
								<div class="card" id="editCard">
									<div class="card-head style-success">
										<header><i class="fa fa-edit"></i> Ask a new question</header>
									</div>
									<%-- <form:form id="questionAskForm" class="form" role="form" modelAttribute="question" > --%>
									<form:form id="questionAskForm" class="form" role="form" modelAttribute="question" action="${contextPath}/questions/create">
										<div class="card-body animsition"><!-- form-inverse -->
											<div class="row">
												<div class="col-md-8">
													
													<div class="row">
														<div class="col-md-10">
															<div class="form-group">
																<form:input type="text" path="questionTitle" class="form-control" id="title" name="title" placeholder="What's your programming question ? Be specific.."></form:input>
																<label for="title" class="primary-dark">Title</label>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-10">
															<div class="form-group">
																<form:textarea type="text" path="questionShortDescription" class="form-control" id="title" name="title" placeholder="Short description to be appeared on listing..." maxlength="120"></form:textarea>
																<label for="title" class="primary-dark">Short Description</label>
															</div>
																<p class="form-text text-muted has-warning">
																  Max 120 characters
																</p>
														</div>
													</div>
													
												</div>
												
												<div class="col-md-4">
													<div class="row">
														<div class="col-md-10">
															<div class="form-group">
																<form:select path="questionTags" class="form-control select2-list" data-placeholder="Select tags" multiple="multiple">
																	<option value="java">Java</option>
																	<option value="c">c</option>
																	<option value="cplusplus">c++</option>
																	<option value="python">python</option>
																	<option value="ruby">ruby</option>
																	<option value="mysql">MySQL</option>
																	<option value="tomcat">Tomcat</option>
																	<option value="iOS">iOS</option>
																	<option value="javascript">Javascript</option>
																	<option value="angular">Angular</option>
																	<option value="nodejs">Node.js</option>
																	<option value="xxxxxx">xxxxx</option>
																	
																</form:select>
																<label>Tags</label>
															</div>
														</div><!--end .col -->
													</div>
												</div>
											</div><!--end .row -->
										</div><!--end .card-body -->

										<div class="card-body tab-content">
												<div class="form-group">
												<form:textarea path="questionDescription" id="description" name="description" class="form-control control-4-rows" spellcheck="false"></form:textarea>
												<form:input type="hidden" path="questionShortDescription" id="questionShortDescription" name="questionShortDescription"></form:input>
												<!-- <div id="summernote1">
													<h2>WYSIWYG Editor</h2>
													Lorem ipsum dolor sit amet, consectetur adipiscing elit.
													Aliquam ullamcorper sapien non nisl facilisis bibendum in
													quis tellus. Duis in urna bibendum turpis pretium
													fringilla. Aenean neque velit, porta eget mattis ac,
													imperdiet quis nisi. Donec non dui et tortor vulputate
													luctus. Praesent consequat rhoncus velit, ut molestie arcu
													venenatis sodales.
													<h3>Lacinia</h3>
													<ul>
														<li>Suspendisse tincidunt urna ut velit ullamcorper
															fermentum.</li>
														<li>Nullam mattis sodales lacus, in gravida sem
															auctor at.</li>
														<li>Praesent non lacinia mi.</li>
														<li>Mauris a ante neque.</li>
														<li>Aenean ut magna lobortis nunc feugiat sagittis.</li>
													</ul>
													<h3>Pellentesque adipiscing</h3>
													Maecenas quis ante ante. Nunc adipiscing rhoncus rutrum.
													Pellentesque adipiscing urna mi, ut tempus lacus ultrices
													ac. Pellentesque sodales, libero et mollis interdum, dui
													odio vestibulum dolor, eu pellentesque nisl nibh quis nunc.
													Sed porttitor leo adipiscing venenatis vehicula. Aenean
													quis viverra enim. Praesent porttitor ut ipsum id ornare.
												</div> -->
											</div>
											
										</div><!--end .card-body.tab-content -->
										<!-- END FORM TAB PANES -->

										<!-- BEGIN FORM FOOTER -->
										<div class="card-actionbar">
											<div class="card-actionbar-row">
												<a class="btn btn-flat" href="../../../html/pages/contacts/search.html">CANCEL</a>
												<button type="submit" class="btn ink-reaction btn-raised btn-primary">Post your question</button>
											</div><!--end .card-actionbar-row -->
										</div><!--end .card-actionbar -->
										<!-- END FORM FOOTER -->

									</form:form>
								</div><!--end .card -->
							</div><!--end .col -->
							<!-- END ADD CONTACTS FORM -->

						</div><!--end .row -->
					</div><!--end .section-body -->
				</section>
			</div><!--end #content-->
			<!-- END CONTENT -->

			<!-- BEGIN MENUBAR-->
			<!--end #menubar-->
			<!-- END MENUBAR -->

			<!-- BEGIN OFFCANVAS RIGHT -->
			<div class="offcanvas">

				<!-- BEGIN OFFCANVAS SEARCH -->
				<div id="offcanvas-search" class="offcanvas-pane width-8">
					<div class="offcanvas-head">
						<header class="text-primary">Search</header>
						<div class="offcanvas-tools">
							<a class="btn btn-icon-toggle btn-default-light pull-right" data-dismiss="offcanvas">
								<i class="md md-close"></i>
							</a>
						</div>
					</div>
					<div class="offcanvas-body no-padding">
						<ul class="list ">
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>A</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar4.jpg?1404026791" alt="" />
									</div>
									<div class="tile-text">
										Alex Nelson
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar9.jpg?1404026744" alt="" />
									</div>
									<div class="tile-text">
										Ann Laurens
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>J</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar2.jpg?1404026449" alt="" />
									</div>
									<div class="tile-text">
										Jessica Cruise
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar8.jpg?1404026729" alt="" />
									</div>
									<div class="tile-text">
										Jim Peters
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>M</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar5.jpg?1404026513" alt="" />
									</div>
									<div class="tile-text">
										Mabel Logan
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar11.jpg?1404026774" alt="" />
									</div>
									<div class="tile-text">
										Mary Peterson
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar3.jpg?1404026799" alt="" />
									</div>
									<div class="tile-text">
										Mike Alba
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>N</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar6.jpg?1404026572" alt="" />
									</div>
									<div class="tile-text">
										Nathan Peterson
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>P</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar7.jpg?1404026721" alt="" />
									</div>
									<div class="tile-text">
										Philip Ericsson
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
							<li class="tile divider-full-bleed">
								<div class="tile-content">
									<div class="tile-text"><strong>S</strong></div>
								</div>
							</li>
							<li class="tile">
								<a class="tile-content ink-reaction" href="#offcanvas-chat" data-toggle="offcanvas" data-backdrop="false">
									<div class="tile-icon">
										<img src="${contextPath}/resources/img/avatar10.jpg?1404026762" alt="" />
									</div>
									<div class="tile-text">
										Samuel Parsons
										<small>123-123-3210</small>
									</div>
								</a>
							</li>
						</ul>
					</div><!--end .offcanvas-body -->
				</div><!--end .offcanvas-pane -->
				<!-- END OFFCANVAS SEARCH -->

				<!-- BEGIN OFFCANVAS CHAT -->
				<div id="offcanvas-chat" class="offcanvas-pane style-default-light width-12">
					<div class="offcanvas-head style-default-bright">
						<header class="text-primary">Chat with Ann Laurens</header>
						<div class="offcanvas-tools">
							<a class="btn btn-icon-toggle btn-default-light pull-right" data-dismiss="offcanvas">
								<i class="md md-close"></i>
							</a>
							<a class="btn btn-icon-toggle btn-default-light pull-right" href="#offcanvas-search" data-toggle="offcanvas" data-backdrop="false">
								<i class="md md-arrow-back"></i>
							</a>
						</div>
						<form class="form">
							<div class="form-group floating-label">
								<textarea name="sidebarChatMessage" id="sidebarChatMessage" class="form-control autosize" rows="1"></textarea>
								<label for="sidebarChatMessage">Leave a message</label>
							</div>
						</form>
					</div>
					<div class="offcanvas-body">
						<ul class="list-chats">
							<li>
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar1.jpg?1403934956" alt="" /></div>
									<div class="chat-body">
										Yes, it is indeed very beautiful.
										<small>10:03 pm</small>
									</div>
								</div><!--end .chat -->
							</li>
							<li class="chat-left">
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar9.jpg?1404026744" alt="" /></div>
									<div class="chat-body">
										Did you see the changes?
										<small>10:02 pm</small>
									</div>
								</div><!--end .chat -->
							</li>
							<li>
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar1.jpg?1403934956" alt="" /></div>
									<div class="chat-body">
										I just arrived at work, it was quite busy.
										<small>06:44pm</small>
									</div>
									<div class="chat-body">
										I will take look in a minute.
										<small>06:45pm</small>
									</div>
								</div><!--end .chat -->
							</li>
							<li class="chat-left">
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar9.jpg?1404026744" alt="" /></div>
									<div class="chat-body">
										The colors are much better now.
									</div>
									<div class="chat-body">
										The colors are brighter than before.
										I have already sent an example.
										This will make it look sharper.
										<small>Mon</small>
									</div>
								</div><!--end .chat -->
							</li>
							<li>
								<div class="chat">
									<div class="chat-avatar"><img class="img-circle" src="${contextPath}/resources/img/avatar1.jpg?1403934956" alt="" /></div>
									<div class="chat-body">
										Are the colors of the logo already adapted?
										<small>Last week</small>
									</div>
								</div><!--end .chat -->
							</li>
						</ul>
					</div><!--end .offcanvas-body -->
				</div><!--end .offcanvas-pane -->
				<!-- END OFFCANVAS CHAT -->

			</div><!--end .offcanvas-->
			<!-- END OFFCANVAS RIGHT -->

		</div><!--end #base-->
		<!-- END BASE -->

		<!-- BEGIN JAVASCRIPT -->

		<!-- BEGIN EXPERIENCE TEMPLATES -->
		
		<!-- END EXPERIENCE TEMPLATES -->

		<!-- BEGIN SKILLS TEMPLATES -->
		
		<!-- END SKILLS TEMPLATES -->

		<script src="${contextPath}/resources/js/libs/jquery/jquery-1.11.2.min.js"></script>
		<script src="${contextPath}/resources/js/libs/jquery/jquery-migrate-1.2.1.min.js"></script>
		<script src="${contextPath}/resources/js/libs/jquery-ui/jquery-ui.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap/bootstrap.min.js"></script>
		<script src="${contextPath}/resources/js/libs/spin.js/spin.min.js"></script>
		<script src="${contextPath}/resources/js/libs/autosize/jquery.autosize.min.js"></script>
		<script src="${contextPath}/resources/js/libs/inputmask/jquery.inputmask.bundle.min.js"></script>
		<script src="${contextPath}/resources/js/libs/moment/moment.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-multiselect/bootstrap-multiselect.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-rating/bootstrap-rating-input.min.js"></script>
		<script src="${contextPath}/resources/js/libs/nanoscroller/jquery.nanoscroller.min.js"></script>
		<script src="${contextPath}/resources/js/libs/microtemplating/microtemplating.min.js"></script>
		
		<script src="${contextPath}/resources/js/libs/summernote/summernote.min.js"></script>
		<script src="${contextPath}/resources/js/libs/summernote/summernote-ext-highlight.js"></script>
		<script src="${contextPath}/resources/js/libs/google-code-prettify/prettify.js"></script>
		
		<script src="${contextPath}/resources/js/libs/select2/select2.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
		<script src="${contextPath}/resources/js/libs/multi-select/jquery.multi-select.js"></script>
		<script src="${contextPath}/resources/js/libs/inputmask/jquery.inputmask.bundle.min.js"></script>
		<script src="${contextPath}/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js"></script>
		<script src="${contextPath}/resources/js/libs/typeahead/typeahead.bundle.min.js"></script>
		<script src="${contextPath}/resources/animsition/js/animsition.min.js"></script>
		<script src="${contextPath}/resources/js/core/source/App.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavigation.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppOffcanvas.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppCard.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppForm.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavSearch.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppVendor.js"></script>
		<script src="${contextPath}/resources/js/core/demo/Demo.js"></script>
		<script src="${contextPath}/resources/js/core/demo/DemoPageContacts.js"></script>
		<!-- END JAVASCRIPT -->

<script type="text/javascript">
$( document ).ready(function() {
	  "use strict";
	/* ------------------------------------- */
	/* Page Loading    ................... */
	/* ------------------------------------- */


	  $(".animsition").animsition({
	    inClass: 'fade-in',
	    outClass: 'fade-out',
	    inDuration: 1500,
	    outDuration: 800,
// 	    linkElement: '.a-link',
	    // e.g. linkElement: 'a:not([target="_blank"]):not([href^="#"])'
	    loading: true,
	    loadingParentElement: 'html', //animsition wrapper element
	    loadingClass: 'animsition-loading',
	    loadingInner: '', // e.g '<img src="loading.svg" />
	    timeout: false,
	    timeoutCountdown: 5000,
	    onLoadEvent: true,
	    browser: [ 'animation-duration', '-webkit-animation-duration'],
	    // "browser" option allows you to disable the "animsition" in case the css property in the array is not supported by your browser.
	    // The default setting is to disable the "animsition" in a browser that does not support "animation-duration".
	    overlay : false,
	    overlayClass : 'animsition-overlay-slide',
	    overlayParentElement : 'body',
	    transition: function(url){ window.location.href = url; }
	  });

	  /* -------------------------------------------------- */
		/* Update summernote question short-description     */
		/* ------------------------------------------------ */
	
	
	document.getElementById('questionAskForm').onsubmit = function(e) {
		var cleanText = $("#description").code();
		//var cleanText = code.replace(/<\/?[^>]+(>|$)/g, "");
		//cleanText = text.text();
		if (cleanText.length >= 200) {
			cleanText = cleanText.substring(0, 190);
		}
		cleanText = cleanText.replace(/<\/?[^>]+(>|$)/g, "");
		document.getElementById('questionShortDescription').value = cleanText;

	};

					});
</script>

	</body>
</html>
>>>>>>> post-chat
