<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="aside">
    <div class="label">CATEGORY</div>
    <ul>
		<c:forEach var="category" items="${ categories }">
        	<a href="category?id=${ category.getId() }"><li>${ category.getName() }</li></a>
		</c:forEach>
    </ul>
</div>