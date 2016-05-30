<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="pageTitle" value="Add Product" scope="request" />
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
					<form method="POST" modelAttribute="product">
						<p>Name:</p> <input type="text" name="name" class="customInput" /> 
						<p>Quantity:</p> <input type="number" name="quantity" class="customInput" /> <br />
						<p>Price:</p> <input type="number" step="0.01" name="price" class="customInput" /> <br />
						<p>Category:</p>
						<select name="category">
							<c:forEach var="category" items="${ categories }">
								<option value="${ category.getId() }">${ category.getName() }</option>
							</c:forEach>
						</select>
						<p>Picture Url:</p> <input type="text" name="pictureUrl" class="customInput" /> <br />
						<p>Description:</p> <input type="text" name="description" class="customInput" /> <br />
						<br />
						<br />
						<input type="submit" class="customButton" value="Add Product" name="customButton" />
					</form>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>