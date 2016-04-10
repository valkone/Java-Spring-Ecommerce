<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="aside">
    <div class="label">CATEGORY</div>
    <ul>
		<c:forEach var="category" items="${ categories }">
        	<a href="category/${ category.getId() }"><li>${ category.getCategory() }</li></a>
		</c:forEach>
    </ul>
</div>