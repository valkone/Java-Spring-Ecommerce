<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Login" scope="request" />
<%@ include file="partial-views/header.jsp" %>

<div id="content">
	<%@ include file="partial-views/aside.jsp"%>
	<div id="main-content">
		<div id="mcontent">
			<c:if test="${cart != null && cart.size() > 0}">
				<table border="0" class="table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Quantity</th>
							<th>Total Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${cart}">
							<tr>
								<td>${ product.name }</td>
								<td>${ product.quantity }</td>
								<td>${ product.price * product.quantity }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test="${cart == null || cart.size() == 0}">
						There is no products
			</c:if>
		</div>
	</div>
	<div class="clear"></div>
</div>
 
<%@ include file="partial-views/footer.jsp" %>