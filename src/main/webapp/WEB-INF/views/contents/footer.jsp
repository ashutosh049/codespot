<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="messages" var="msg" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!--Footer-->

<!--/.Footer-->
<!-- <footer>
	<div class="card">
		<div class="card-head">
			<div class="tools">
				<div class="btn-group">
					<div class="btn-group">
						<a href="#" class="btn btn-icon-toggle dropdown-toggle"
							data-toggle="dropdown"><i class="md md-colorize"></i></a>
						<ul
							class="dropdown-menu animation-dock pull-right menu-card-styling"
							role="menu" style="text-align: left;">
							<li><a href="javascript:void(0);"
								data-style="style-default-dark"><i
									class="fa fa-circle fa-fw text-default-dark"></i> Default dark</a></li>
							<li><a href="javascript:void(0);" data-style="style-default"><i
									class="fa fa-circle fa-fw text-muted"></i> Default</a></li>
							<li><a href="javascript:void(0);"
								data-style="style-default-light"><i
									class="fa fa-circle fa-fw text-default-light"></i> Default
									light</a></li>
							<li><a href="javascript:void(0);"
								data-style="style-default-bright"><i
									class="fa fa-circle fa-fw text-default-bright"></i> Default
									bright</a></li>
							<li><a href="javascript:void(0);"
								data-style="style-primary-dark"><i
									class="fa fa-circle fa-fw text-primary-dark"></i> Primary dark</a></li>
							<li><a href="javascript:void(0);" data-style="style-primary"><i
									class="fa fa-circle fa-fw text-primary"></i> Primary</a></li>
							<li><a href="javascript:void(0);"
								data-style="style-primary-light"><i
									class="fa fa-circle fa-fw text-primary-light"></i> Primary
									light</a></li>
							<li><a href="javascript:void(0);"
								data-style="style-primary-bright"><i
									class="fa fa-circle fa-fw text-primary-bright"></i> Primary
									bright</a></li>
							<li><a href="javascript:void(0);"
								data-style="style-accent-dark"><i
									class="fa fa-circle fa-fw text-accent-dark"></i> Accent dark</a></li>
							<li><a href="javascript:void(0);" data-style="style-accent"><i
									class="fa fa-circle fa-fw text-accent"></i> Accent</a></li>
							<li><a href="javascript:void(0);"
								data-style="style-accent-light"><i
									class="fa fa-circle fa-fw text-accent-light"></i> Accent light</a></li>
							<li><a href="javascript:void(0);"
								data-style="style-accent-bright"><i
									class="fa fa-circle fa-fw text-accent-bright"></i> Accent
									bright</a></li>
							<li><a href="javascript:void(0);" data-style="style-danger"><i
									class="fa fa-circle fa-fw text-danger"></i> Danger</a></li>
							<li><a href="javascript:void(0);" data-style="style-warning"><i
									class="fa fa-circle fa-fw text-warning"></i> Warning</a></li>
							<li><a href="javascript:void(0);" data-style="style-success"><i
									class="fa fa-circle fa-fw text-success"></i> Success</a></li>
							<li><a href="javascript:void(0);" data-style="style-info"><i
									class="fa fa-circle fa-fw text-info"></i> Info</a></li>
						</ul>
					</div>
					<a class="btn btn-icon-toggle btn-refresh"><i
						class="md md-refresh"></i></a> <a
						class="btn btn-icon-toggle btn-collapse"><i
						class="fa fa-angle-down"></i></a> <a
						class="btn btn-icon-toggle btn-close"><i class="md md-close"></i></a>
				</div>
			</div>
			<header>Header</header>
		</div>
		<div class="card-body style-primary">
			<p>Ad ius duis dissentiunt, an sit harum primis persecuti,
				adipisci tacimates mediocrem sit et. Id illud voluptaria omittantur
				qui, te affert nostro mel. Cu conceptam vituperata temporibus has.</p>
		</div>
	</div>
</footer> -->
<footer class="page-footer center-on-small-only blue-grey lighten-5 pt-0">

    <div style="background-color: #009935;">
        <div class="container">
            <!--Grid row-->
            <div class="row py-4 d-flex align-items-center">

                <!--Grid column-->
				<div class="card-body style-primary large-padding text-center">
						<strong>Get connected with us on social networks!</strong>
					<h6 class="mb-0 text-white text-center text-md-left">
					</h6>
				</div>
				<!--Grid column-->

                <!--Grid column-->
                <div class="col-12 col-md-7 text-center text-md-right">
                    <!--Facebook-->
                    <a class="icons-sm fb-ic ml-0"><i class="fa fa-facebook white-text mr-lg-4"> </i></a>
                    <!--Twitter-->
                    <a class="icons-sm tw-ic"><i class="fa fa-twitter white-text mr-lg-4"> </i></a>
                    <!--Google +-->
                    <a class="icons-sm gplus-ic"><i class="fa fa-google-plus white-text mr-lg-4"> </i></a>
                    <!--Linkedin-->
                    <a class="icons-sm li-ic"><i class="fa fa-linkedin white-text mr-lg-4"> </i></a>
                    <!--Instagram-->
                    <a class="icons-sm ins-ic"><i class="fa fa-instagram white-text mr-lg-4"> </i></a>
                </div>
                <!--Grid column-->

            </div>
            <!--Grid row-->
        </div>
    </div>

    <!--Footer Links-->
    <div class="container mt-5 mb-4 text-center text-md-left">
        <div class="row mt-3">

            <!--First column-->
            <div class="col-md-3 col-lg-4 col-xl-3 mb-r dark-grey-text">
                <h6 class="title font-bold"><strong>Company name</strong></h6>
                <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p>Here you can use rows and columns here to organize your footer content. Lorem ipsum dolor sit
                    amet, consectetur adipisicing elit.</p>
            </div>
            <!--/.First column-->

            <!--Second column-->
            <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-r dark-grey-text">
                <h6 class="title font-bold"><strong>Products</strong></h6>
                <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p><a href="#!" class="dark-grey-text">MDBootstrap</a></p>
                <p><a href="#!" class="dark-grey-text">MDWordPress</a></p>
                <p><a href="#!" class="dark-grey-text">BrandFlow</a></p>
                <p><a href="#!" class="dark-grey-text">Bootstrap Angular</a></p>
            </div>
            <!--/.Second column-->

            <!--Third column-->
            <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-r dark-grey-text">
                <h6 class="title font-bold"><strong>Useful links</strong></h6>
                <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p><a href="#!" class="dark-grey-text">Your Account</a></p>
                <p><a href="#!" class="dark-grey-text">Become an Affiliate</a></p>
                <p><a href="#!" class="dark-grey-text">Shipping Rates</a></p>
                <p><a href="#!" class="dark-grey-text">Help</a></p>
            </div>
            <!--/.Third column-->

            <!--Fourth column-->
            <div class="col-md-4 col-lg-3 col-xl-3 dark-grey-text">
                <h6 class="title font-bold"><strong>Contact</strong></h6>
                <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p><i class="fa fa-home mr-3"></i> New York, NY 10012, US</p>
                <p><i class="fa fa-envelope mr-3"></i> info@example.com</p>
                <p><i class="fa fa-phone mr-3"></i> + 01 234 567 88</p>
                <p><i class="fa fa-print mr-3"></i> + 01 234 567 89</p>
            </div>
            <!--/.Fourth column-->

        </div>
    </div>
    <!--/.Footer Links-->

    <!-- Copyright-->
    <div class="footer-copyright">
        <div class="container-fluid">
            © 2017 Copyright: <a href="https://www.MDBootstrap.com"><strong> MDBootstrap.com</strong></a>
        </div>
    </div>
    <!--/.Copyright -->
    
</footer>