<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <div id="copyright" style="background-color: #20252b; border-color: #20252b; color: #ffffff;">
            <div class="container">
		<div class="col-md-12">
			<p class="pull-left" style="vertical-align: middle;">&copy; 2018.
				Codespot</p>

			<p class="pull-left" style="vertical-align: middle;">
				&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="mailto:kumar.ashutosh.dubey@gmail.com?Subject=Hello%20Ashutosh"
					target="_top"> <i class="fa fa-envelope"> </i>
				</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</p>

			<p class="pull-left" style="vertical-align: middle;">
				<a href="https://github.com/ashutosh049"> <i
					class="fa fa-github"> </i>
				</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p class="pull-right" style="vertical-align: middle;">
				Project by <a href="https://github.com/ashutosh049">Ashutosh</a> <img
					class="img-circle" src="${contextPath}/resources/img/avtars/me.jpg"
					alt="" style="height: 30px;">
			</p>

		</div>
	</div>
        </div>
<!-- </footer> -->