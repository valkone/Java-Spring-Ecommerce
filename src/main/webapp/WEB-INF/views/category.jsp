<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="pageTitle" value="Home" scope="request" />
<%@ include file="partial-views/header.jsp" %>

        <div id="content">
            <%@ include file="partial-views/aside.jsp" %>
            <div id="main-content">
                <div class="product-section">
                    <div class="label">BESTSELLERS</div>
                   	
                   	<c:forEach var="product" items="${ products }">
	                    <div class="product">
	                        <a href="product/${ product.getId() }">
	                            <div class="product-image">
	                                <img src="images/product.jpg"/>
	                            </div>
	                            <div class="product-name">
	                                ${ product.getName() }
	                            </div>
	                            <div class="product-price">
	                                $${ product.getPrice() }
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