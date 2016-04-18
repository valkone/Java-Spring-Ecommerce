<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.valentin.shop.constants.GeneralConstants"%>
<%@ page import="com.valentin.shop.constants.PathConstants"%>

<c:set var="pageTitle" value="Home" scope="request" />
<%@ include file="partial-views/header.jsp"%>
<div id="content">
	<%@ include file="partial-views/aside.jsp"%>
	<div id="main-content">
		<div class="product-section">
			<div class="label">BESTSELLERS</div>
			<div id="allProducts"></div>
			<a href="#" id="loadMoreResults">Load more results</a>
			<div class="clear"></div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<input type="hidden" id="currentPage" value="0" />
<script type="text/javascript">
	if(parseInt($("#currentPage").val()) == 0) {
		loadNextPage();
	}

	$("#loadMoreResults").click(function() {
		loadNextPage();
	});
	
	function loadNextPage() {
		var page = parseInt($("#currentPage").val()) + 1;
		var path = "${ PathConstants.GET_PRODUCTS_BY_PAGES }".replace("/", "");
		var requestUrl = path + "?page=" + page;
		$.get( requestUrl, function( data ) {
			if(data.length > 0) {
				 console.log("asdasd");
				$("#currentPage").val(page);
				data.forEach(function( product ) {
					var productImage = product.pictureUrl != null ? product.pictureUrl : "${ GeneralConstants.DEFAULT_PICTURE_URL }";
					var product = "<div class='product'>" +
						"<a href='product?id="+ product.id +"'>" +
							"<div class='product-image'>" +
								"<img src='" + productImage + "' />" +
							"</div>" +
							"<div class='product-name'>" + product.name + "</div>" +
							"<div class='product-price'>$" + product.price + "</div>" +
						"</a>" +
					"</div>";
					
					$("#allProducts").append(product);
				});
			} else {
				$("#loadMoreResults").hide();
			}
		});	
	}
</script>

<%@ include file="partial-views/footer.jsp"%>