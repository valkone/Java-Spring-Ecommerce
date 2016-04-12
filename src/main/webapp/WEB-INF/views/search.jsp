<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<c:set var="pageTitle" value="Login" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div id="mcontent">
					Search: <input type="text" name="search" id="title" /><br />
					Minimum Price: <input type="text" id="priceMin" /><br /> 
					Max Price: <input type="text" id="priceMax" /><br />
					Quantity(at least): <input type="text" id="quantity" /><br />
					<button id="searchButton" class="custonButton">Search</button>
					<br /><br />
					
					<div id="products"></div>
					
            		<div class="clear"></div>
				</div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>

<script type="text/javascript">
	$("#searchButton").click(function() {
		$("#products").empty();
		
		var title = $("#title").val();
		var priceMin = $("#priceMin").val();
		var priceMax = $("#priceMax").val();
		var quantity = $("#quantity").val();
		
		if(priceMax == "") {
			priceMax = 0;
		}
		if(priceMin == "") {
			priceMin = 0;
		}
		if(quantity == "") {
			quantity = 0;
		}
		
		var requestUrl = "productSearch?title=" + title + "&minPrice=" + priceMin + "&maxPrice=" + priceMax +
				"&quantity=" + quantity;
		
		$.get( requestUrl, function( data ) {
			 data.forEach(function( product ) {
				 var product = "<div class='product'>" +
					"<div class='productName'><a href='product?id="+ product.id +"'>"+ product.name +"</a></div>" +
					"<div class='productQuantity'>Quantity: "+ product.quantity +"</div>" +
					"<div class='productPrice'>Price: $"+ product.price +"</div>" +
					"</div>";
				$("#products").append(product);
			 });
		});	
	})
</script>