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
					<c:if test="${ product == null }">
						<div class="error">
							${ productError }
						</div>
					</c:if>
					<form method="POST" modelAttribute="product">
						<p>Name:</p> <input type="text" name="name" value="${ product.getName() }" class="customInput" /> 
						<p>Quantity:</p> <input type="number" name="quantity" value="${ product.getQuantity() }" class="customInput" /> <br />
						<p>Price:</p> <input type="number" step="0.01" name="price" value="${ product.getPrice() }" class="customInput" /> <br />
						<br />
						<input type="submit" class="customButton" value="Edit Product" name="customButton" />
					</form>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>