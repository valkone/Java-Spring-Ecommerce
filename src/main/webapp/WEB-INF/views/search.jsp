<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<c:set var="pageTitle" value="Login" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div id="mcontent">
					Search: <input type="text" name="search" id="title" />
					<button id="searchButton" class="custonButton">Search</button>
					<br /><br />
					
					<div id="products">
						<div class="product">
							<div class="productName">Laptop</div>
							<div class="productQuantity">Quantity: 20</div>
							<div class="productPrice">Price: $20</div>
						</div>
					</div>
					
            		<div class="clear"></div>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>

<script type="text/javascript">
	$("#searchButton").click(function() {
		var title = $("#title").val();
		$("#products").empty();
		$.get( "productSearch?title=" + title, function( data ) {
			 data.forEach(function( product ) {
				 var product = "<div class='product'>" +
					"<div class='productName'>"+ product.name +"</div>" +
					"<div class='productQuantity'>Quantity: "+ product.quantity +"</div>" +
					"<div class='productPrice'>Price: $"+ product.price +"</div>" +
					"</div>";
				$("#products").append(product);
			 });
		});	
	})
</script>