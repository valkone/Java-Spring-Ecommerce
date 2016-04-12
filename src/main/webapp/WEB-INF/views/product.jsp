<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Home" scope="request" />
<%@ include file="partial-views/header.jsp" %>
    <div id="content">
        <div id="aside">
            <div class="label">Tags</div>
            <div id="tags">
                <a href="#">Women</a>
                <a href="#">Fashion</a>
                <a href="#">Fashion</a>
                <a href="#">Kids</a>
                <a href="#">Kids</a>
                <a href="#">Kids</a>
                <a href="#">New</a>
            </div>
        </div>
        <div id="main-content">
            <div id="product">
            <div id="message"></div>
                <div id="product-picture">
                    <div id="main-pic">
                        <img src="${ product.getPictureUrl() }" />
                    </div>
                    <!-- <div id="small-pics">
                        <div class="small-pic">
                            <img src="images/product.jpg" />
                        </div>
                        <div class="small-pic">
                            <img src="images/product.jpg" />
                        </div>
                        <div class="small-pic">
                            <img src="images/product.jpg" />
                        </div>
                    </div> -->
                </div>
                <div id="product-description">
                    <h1>${ product.getName() }</h1>
                    <b>Category:</b>  <span><a href="category?id=${ product.getCategory().getId() }">${ product.getCategory().getName() }</a></span>
                    <br />
                    <br />
                    <div class="hr"></div>
                    <div class="product-price" style="padding: 13px 0;font-size: 23px;">
                        $${ product.getPrice() }
                    </div>
                    <div class="hr"></div>
                    <br />
                    <div class="label">Description:</div>
                    <div id="description">
                        ${ product.getDescription() }
                    </div>
                    <div id="available">
                        ${ product.getQuantity() } items <span>In stock</span>
                    </div>
                    <div id="quantity">
                        Quantity
                        <input type="text" id="quantity-input" value="1"/>
                        <a href="javascript: void(0);" id="add-quantity">+</a>
                        <a href="javascript: void(0);" id="remove-quantity">-</a>
                    </div>
                    <div id="buy-button-content">
                        <button id="buy-button">ADD TO CARD</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
<%@ include file="partial-views/footer.jsp" %>
<script type="text/javascript">
	$("#buy-button").click(function() {
		var productId = ${ product.getId() };
		var quantity = $("#quantity-input").val();
		
		var requestUrl = "addProductToCart?productId=" + productId + "&quantity=" + quantity;

		$.get( requestUrl, function( status ) {
			$("#message").empty();
			if(status.successful) {
				var message = "<div class='success'>"+ status.successMessage +"</div>";
				$("#message").append(message);
			} else {
				status.errors.forEach(function( error ) {
					var message = "<div class='error'>"+ error +"</div>";
					$("#message").append(message);
				});
			}
		});	
	});

    $("#add-quantity").click(function(){
        var currentQuantity = $("#quantity-input").val();
        var newQuantity = parseFloat(currentQuantity) + 1;
        var maxQuantity = ${ product.getQuantity() };
        if(newQuantity <= maxQuantity) {
        	$("#quantity-input").val(newQuantity);
        }
    });

    $("#remove-quantity").click(function(){
        var currentQuantity = $("#quantity-input").val();
        var newQuantity = parseFloat(currentQuantity) - 1;
        if(newQuantity > 0) {
            $("#quantity-input").val(newQuantity);
        }
    });
</script>