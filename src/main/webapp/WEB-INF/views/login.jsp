<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="pageTitle" value="Login" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div id="mcontent">
					<form name="login" method="POST">
						<p>Username:</p> <input type="text" name="username" class="customInput" /> 
						<p>Password:</p> <input type="text" name="password" class="customInput" /> <br />
						<br />
						<input type="submit" class="customButton" value="Login" name="loginButton" />
					</form>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>