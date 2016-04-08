<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="pageTitle" value="Login" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div id="mcontent">
					<button class="customButton" onclick="location.href='addProduct'">Add Product</button><br /><br /><br />
					<c:if test="${products != null && products.size() > 0}">
						<table border="0" class="table">
							<thead>
								<tr>
									<th>Name</th>
									<th>Quantity</th>
									<th>Price</th>
									<th>Edit</th>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${products}">
									<tr>
										<td>${ product.getName() }</td>
										<td>${ product.getQuantity() }</td>
										<td>${ product.getPrice() }</td>
										<td><button onClick="location.href='productEdit?id=${ product.getId() }'" class="customButton">Edit</button></td>
										<td><button class="customButton">Delete</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${products == null || products.size() == 0}">
						There is no products
					</c:if>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>