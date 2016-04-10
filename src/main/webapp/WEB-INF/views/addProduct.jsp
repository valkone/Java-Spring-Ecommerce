<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="pageTitle" value="Add Product" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div id="mcontent">
                	<c:if test="${status.isSuccess()}">
						<div class="success">
							${status.getSuccessMessage()}
						</div>
					</c:if>
					<form method="POST" modelAttribute="product">
						<p>Name:</p> <input type="text" name="name" class="customInput" /> 
						<p>Quantity:</p> <input type="text" name="quantity" class="customInput" /> <br />
						<p>Price:</p> <input type="text" name="price" class="customInput" /> <br />
						<p>Category:</p>
						<select name="category">
							<c:forEach var="category" items="${ categories }">
								<option value="${ category.getId() }">${ category.getCategory() }</option>
							</c:forEach>
						</select>
						<br />
						<br />
						<input type="submit" class="customButton" value="Add Product" name="customButton" />
					</form>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>