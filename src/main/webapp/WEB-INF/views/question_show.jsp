<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"	 uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form"	 uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c"		 uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"		 uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="messages" var="msg" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Codespot</title>

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
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/animsition/css/animsition.min.css" />
		
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/css/theme-4/libs/summernote/summernote.css?1425218701" />
		<link type="text/css" rel="stylesheet" href="${contextPath}/resources/js/libs/google-code-prettify/prettify.css" />
		<!-- END STYLESHEETS -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script type="text/javascript" src="${contextPath}/resources/js/libs/utils/html5shiv.js?1403934957"></script>
		<script type="text/javascript" src="${contextPath}/resources/js/libs/utils/respond.min.js?1403934956"></script>
		<![endif]-->
		
		<style type="text/css">
			.note-editable{
				background-color: white;
			}
		</style>
		
	</head>
	<!-- <body class="menubar-hoverable header-fixed " onload="connect()();PR.prettyPrint()"> -->
	<body class="menubar-hoverable header-fixed " onload="connect();">
<%-- 		<c:set var="userInContext"></c:set> --%>
		<input type="hidden" id="questionInContext" value="${question}">
<%-- 		<input type="text" id="questionInContextId" value="${question.questionId}"> --%>
		<input type="hidden" id="userInContext" value="${question.user}">
		<!-- BEGIN HEADER-->
		<jsp:include page="contents/header.jsp"/>
		<!-- END HEADER-->

		<!-- BEGIN BASE-->
		<div id="base">

		<!-- BEGIN CONTENT-->
		<div id="content">
			<section>
				<div class="section-body contain-lg">
					<div class="row-fluid">
						<div class="col-lg-9">
							<div class="QUESTION">
								<div class="card card-underline animsition">
									<div class="card-head">
										<!-- <header><h1 class="text-primary"><a href="${contextPath}/questions/${question.questionId}/${question.questionTitle}" style="text-decoration: none;">${question.questionTitle}</a></h1></header> -->
										<header>
											<div class="margin-bottom-xxl"></div>
											<p class="text-primary">
												<a
													href="${contextPath}/questions/${question.questionId}/${question.questionTitle}"
													style="text-decoration: none;">
													${question.questionTitle} </a>
											</p>
										</header>
									</div>
									<div class="card-body tab-content">
										<div class="tab-pane active" id="web1">
											<div class="row">
												<div class="">
													<div class="margin-bottom-xxl"></div>
													${question.questionDescription}
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-7 list-tags pull-left">
									<a class="btn btn-xs btn-success">Wordpress</a> <a class="btn btn-xs btn-success">CSS3</a> <a class="btn btn-xs btn-success">jQuery</a>

									<a class="btn btn-xs btn-success">Wordpress</a> <a class="btn btn-xs btn-success">CSS3</a> <a class="btn btn-xs btn-success">jQuery</a>
								</div>
								<div class="col-md-4 col-sm-8 pull-right">
									<div class="card">
										<div class="card-body no-padding">
											<div class="alert alert-callout alert-success no-margin">
												<span class="opacity-50" id="createTimestamp">${question.createTimestamp}</span><br />
												<a href=""> <img
													class="img-circle img-responsive width-1"
													src="${contextPath}/resources/img/avtars/a (10).jpg" alt=""
													style="display: inline;" /> <span class="opacity-50">${question.user.userName}</span>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>

						<div class="COMMENTS">
							<h4>5 Comments</h4>
							<ul class="list-comments" id="commentList">
							<c:forEach items="${question.comments}" var="comments">
								<li>
									<div class="card">
										<div class="card-body no-padding no-margin">
											<div class="alert no-margin">
												${comments.commentDescription}
											</div>
											<div class="card-actionbar no-margin">
												<div class="card-actionbar-row">
													<a class="btn btn-default btn-success pull-left" href="#respond">Reply</a>
													<a href="" class="pull-right"> 
														<img class="img-circle img-responsive width-1" src="${contextPath}/resources/img/avtars/face (1).jpg" alt="" style="display: inline;" /> 
														<span class="opacity-50">${comments.user.userName}</span>
														<small></small>
													</a>
												</div>
											</div>
											<!-- <div class="alert no-padding">
												<a class="btn btn-default btn-success stick-bottom-left" href="#respond">Reply</a>
											</div> -->
										</div>
									</div> <!--end .card -->
								</li>
							</c:forEach>
							</ul>
							<security:authorize access="hasRole('ROLE_USER')" var="isUser">
								<ul class="ADD-COMMENT list-comments" id="commentList">
									<li>
										<h4>Leave a comment</h4>
										<div class="card style-default-bright">
											<div class="form-group" style="margin-bottom: 0px;">
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" />
												<textarea id="simple-summernote" name="message"
													class="form-control control-6-rows" spellcheck="false" maxlength="67108"></textarea>
											</div>
	
										</div>
										<div class="margin-bottom-xxl pull-right"
											style="padding-top: 7px;">
											<a class="btn btn-flat" href="#">Cancel</a>
											<button type="button" onclick="postComment();"
												class="btn ink-reaction btn-raised btn-primary">Post
												your comment</button>
										</div>
									</li>
								</ul>
							</security:authorize>
							<span class="header">Login to add comment</span>
						</div>
						<!-- END: Comments -->
					</div>
					
					</div>

					<!-- BEGIN BLOG MENUBAR -->
						<div class="col-lg-3 pull-right">
							<p>
								<a href="${contextPath}/questions/ask"
									class="btn btn-block button-success ink-reaction btn-success"
									style="text-transform: full-width;"><i class="fa fa-edit"></i>
									Ask Question</a>
							</p>
							<div class="card-body style-default-light">
								<h3 class="text-light">${question.user.userName}</h3>
								<p>
									Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras
									mattis consectetur purus sit amet fermentum. Aenean lacinia
									bibendum nulla sed consectetur.
								</p>
								<h3 class="text-light">Archives</h3>
								<ul class="nav nav-pills nav-stacked nav-transparent">
									<li><a href="#"><span class="badge pull-right">42</span>Design</a></li>
									<li><a href="#"><span class="badge pull-right">56</span>January
											2014</a></li>
									<li><a href="#"><span class="badge pull-right">32</span>December
											2013</a></li>
									<li><a href="#"><span class="badge pull-right">19</span>November
											2013</a></li>
									<li><a href="#"><span class="badge pull-right">22</span>October
											2013</a></li>
									<li><a href="#"><span class="badge pull-right">14</span>September
											2013</a></li>
									<li><a href="#"><span class="badge pull-right">5</span>August
											2013</a></li>
								</ul>
								<h3 class="text-light">Elsewhere</h3>
								<ul class="nav nav-pills nav-stacked nav-transparent">
									<li><a href="#">GitHub</a></li>
									<li><a href="#">Twitter</a></li>
									<li><a href="#">Facebook</a></li>
								</ul>
							</div>
							<!-- .card-body -->
						</div>
						<!--end .col -->
							<!--end .col -->
					</div>
				</section>
				</div>
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
		<script src="${contextPath}/resources/js/libs/nanoscroller/jquery.nanoscroller.min.js"></script>\
		<script src="${contextPath}/resources/js/core/moment.min.js"></script>\
		<script src="${contextPath}/resources/js/libs/summernote/summernote.min.js"></script>
		<script src="${contextPath}/resources/js/libs/summernote/summernote-ext-highlight.js"></script>
		<script src="${contextPath}/resources/js/libs/google-code-prettify/prettify.js"></script>
		<script src="${contextPath}/resources/animsition/js/animsition.min.js"></script>
		<script src="${contextPath}/resources/js/core/source/App.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavigation.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppOffcanvas.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppCard.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppForm.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppNavSearch.js"></script>
		<script src="${contextPath}/resources/js/core/source/AppVendor.js"></script>
		<script src="${contextPath}/resources/js/core/demo/Demo.js"></script>
		<script src="${contextPath}/resources/js/core/sockjs-0.3.4.min.js"></script>
		<script src="${contextPath}/resources/js/core/stomp.min.js"></script>
		<!-- END JAVASCRIPT -->
	<script type="text/javascript">
        var stompClient = null; 
        var socket=null;
        var questionInContext = document.getElementById('questionInContext').value;
        var userInContext = document.getElementById('userInContext').value;
        
//         alert("questionInContext.questionId : ${question.questionId}");
        
//         var headerName = "${_csrf.parameterName}";
//         var token = "${_csrf.token}";
//         alert(headerName+" : "+token);
//         var headers = {};
//         headers[headerName] = token;
/*         var headers = {
        	      '_csrf':'${_csrf.token}'
        	    }; */
        //var isUser = '${isUser}';
        
       /*  if(isUser){
        	alert("isUser : "+isUser);
        	connect();
        } */
        function connect() {
        	console.log('connect()............. START');
            socket = new SockJS('${contextPath}/questionComment');
			stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/postComment/${question.questionId}', function(calResult){
                	if(calResult!=null)
                		showResult(JSON.parse(calResult.body));
                });
            }, function( error ) {
                alert( error );
            });
            console.log('connect()............. END');
        }
	    
//         socket.onopen = function() {
//             console.log('>>>>>>>>>>>>>>>socket opened<<<<<<<<<<<<\n');
//             sock.send('test');
//         }
//         socket.onmessage = function(e) {
//             console.log('>>>>>>>>>>>>>>>message<<<<<<<<<<<<\n', e.data);
//             sock.close();
//         };

//         socket.onclose = function() {
//             console.log('>>>>>>>>>>>>>>>close<<<<<<<<<<<<');
//         };
//         socket.onerror = function() {
//             console.log('>>>>>>>>>>>>>>>close<<<<<<<<<<<<');
//         };
        
        
	        function setConnected(connected) {
	        }
	        
	        function disconnect() {
	        	console.log('disconnect()............. START');
	            stompClient.disconnect();
	            setConnected(false);
	            console.log("Disconnected");
	            console.log('disconnect()............. END');
	        }
	        function postComment() {
	        	
	        	console.log('posting comment............. START');
	        	 if(isUser){
	        		    //connect();
			            var commentDescription = $('.note-editable').html();
			            var destination = "/postQuestionComment/questionComment/${question.questionId}";
			            var data = {  'commentDescription': commentDescription,
									  'question' : {'questionId':'${question.questionId}'},
									  'user' : {'userName':'${username}'}
									};
				            stompClient.send(destination, {}, JSON.stringify(data));
/* 			            if(byteCount(JSON.stringify(data)) < 65000){
			            }else{
			            	alert("Comment data too large. Only accepted charactyers 65000 bytes !");
			            } */
	        	 }else{
	        		 console.log('User not logged-in... Abort!');
	        		 alert("Please login to add your comment..");
	        	 }
	        	 console.log('posting comment............. END');
	        }
	        function showResult(comment) {
	        	/* console.log("-------RESULT----");
	        	$.each(result, function(key,value){
		        	console.log(key+" : "+result);
	        	});
	        	console.log("-------RESULT----");
	        	var comment = JSON.parse(result); */
	        	 var cmnt = "<li> <div class='card'>"+
		        	 				"<div class='comment-avatar'>"+
		        	 					"<i class='glyphicon glyphicon-user opacity-50'></i>"+ 
		        	 				 "</div>"+
		        	 				 "<div class='card-body'>"+
		        	 				 	"<h4 class='comment-title'> "+comment.user.userName+" <small>20/06/2013 at 4:02 pm</small> </h4>"+
		        	 				 	"<a class='btn btn-default-dark stick-top-right' href='#respond'>Reply</a>"+
		        	 				 	"<p>"+comment.commentDescription+"</p>"+
		        	 				 "</div>"+
	        	 				"</div>"+
	        	 			"</li>";
	        	 			
	        	console.log('showResult()............. START');
	            $("#commentList").append(cmnt);
	            console.log('showResult()............. END');
	        }
        //}//----isUser
        
        //alert("asked : "+ moment().calendar());
        var d = moment("${question.createTimestamp}");
        $('#createTimestamp').html('asked '+moment(d, "YYYYMMDD h:mm:ss a").fromNow());
        
       /*  var date = formatDate(new Date('${question.createTimestamp}'));
        function formatDate(date) {
        	  var monthNames = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];

        	  var day = date.getDate();
        	  var monthIndex = date.getMonth();
        	  var year = date.getFullYear();
        	  var timeHour  = date.getHours();
        	  var timeMin  = date.getMinutes();

        	  return day + ' ' + monthNames[monthIndex] + ' ' + year +' at '+ timeHour+":"+timeMin;
        	} */
        /* function byteCount(s) {
            return encodeURI(s).split(/%..|./).length - 1;
        } */
        var on_error =  function(error) {
            console.log('error');
        };
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
	    loadingParentElement: '.QUESTION', //animsition wrapper element
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