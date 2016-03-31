<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>${pageTitle}</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<div class="wrapper">
        <div id="header">
            <div id="top-menu">
            	<sec:authorize access="isAuthenticated()">
	                <div id="left-top-menu">
	                    <tab>Welcome <span>${user.username}</span></tab>
	                    <tab>Language: <span>English</span></tab>
	                </div>
                </sec:authorize>
                <div id="right-top-menu">
                	<ul>
                		<sec:authorize access="isAnonymous()">
	                		<li><tab><a href="login">Login</a></tab></li>                        
                        	<li><tab><a href="register">Register</a></tab></li>
                		</sec:authorize>
                    	<sec:authorize access="isAuthenticated()">
	                		<li><tab><a href="myProducts">My Products</a></tab></li>
	                		<li><tab><a href="logout">Logout</a></tab></li>    
                    	</sec:authorize>
                        
                        
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <div id="header-main">
                <div id="logo">
                    <a href="#"><img src="resources/images/logo.jpg" /></a>
                </div>
                <div id="search">
                    <form>
                        <input type="text" name="search" id="search-input" placeholder="Enter your search key ... " />
                        <input type="submit" value="Search" class="customButton" />
                    </form>
                </div>
                <div class="clear"></div>
            </div>
            <div id="main-menu">
                <ul>
                    <li><a href="#">HOME</a></li>
                    <li><a href="#">LEGAL NOTICE</a></li>
                    <li><a href="#">SECURE PAYMENT</a></li>
                    <li><a href="#">ABOUT US</a></li>
                    <li><a href="#">CONTACT US</a></li>
                </ul>
                <div class="clear"></div>
            </div>
        </div>