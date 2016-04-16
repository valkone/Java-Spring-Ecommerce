<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.valentin.shop.constants.GeneralConstants" %>

<c:set var="pageTitle" value="Home" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div class="product-section">
                    <div class="label">BESTSELLERS</div>
                   	
                   	<c:forEach var="product" items="${ products }">
	                    <div class="product">
	                        <a href="product?id=${ product.id }">
	                            <div class="product-image">
	                                <img src="${ product.pictureUrl != null ? product.pictureUrl : 
								GeneralConstants.DEFAULT_PICTURE_URL }"/>
	                            </div>
	                            <div class="product-name">
	                                ${ product.name }
	                            </div>
	                            <div class="product-price">
	                                $${ product.price }
	                            </div>
	                        </a>
	                    </div>
                   	</c:forEach>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        
<%@ include file="partial-views/footer.jsp" %>