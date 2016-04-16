<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="pageTitle" value="Register" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div id="mcontent">
                	<c:if test="${status.isSuccessful() == false}">
						<div class="error">
							<c:forEach items="${status.getErrors()}" var="status">     
							   ${status} <br />
							</c:forEach>
						</div><br />
					</c:if>
					<c:if test="${status.isSuccessful()}">
						<div class="success">
							${status.getSuccessMessage()}
						</div>
					</c:if>
                
					<form method="post" ModelAttribute="registration">
						<p>Username:</p> <input type="text" value="${username}" name="username" class="customInput" /> 
						<p>Password:</p> <input type="password" name="password" class="customInput" /> <br />
						<p>Confirm Password:</p> <input type="password" name="password2" class="customInput" /> <br />
						<p>Email:</p> <input type="text" value="${email}" name="email" class="customInput" /> <br />
						<br />
						<input type="submit" class="customButton" value="Register" name="loginButton" />
					</form>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>