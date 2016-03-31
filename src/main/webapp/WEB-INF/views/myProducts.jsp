<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="pageTitle" value="Login" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div id="mcontent">
					<button class="customButton" onclick="location.href='addProduct'">Add Product</button><br /><br /><br />
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
							<tr>
								<td>Laptop</td>
								<td>20</td>
								<td>1000</td>
								<td><button class="customButton">Edit</button></td>
								<td><button class="customButton">Delete</button></td>
							</tr>
							<tr>
								<td>Laptop</td>
								<td>20</td>
								<td>1000</td>
								<td><button class="customButton">Edit</button></td>
								<td><button class="customButton">Delete</button></td>
							</tr>
							<tr>
								<td>Laptop dqw dqw dqw dqwd qwdqw dqw dqw dqwd qwd qwd</td>
								<td>20</td>
								<td>1000</td>
								<td><button class="customButton">Edit</button></td>
								<td><button class="customButton">Delete</button></td>
							</tr>
						</tbody>
					</table>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>