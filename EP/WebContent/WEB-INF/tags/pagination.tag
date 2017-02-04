<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="itemsTotal" required="true" rtexprvalue="true"
	type="java.lang.Integer"%>
<%@ attribute name="action" required="true" rtexprvalue="true"
	type="java.lang.String"%>
<%@ attribute name="itemsPerPage" required="true" rtexprvalue="true"
	type="java.lang.Integer"%>



<fmt:parseNumber var="pagesTotal" integerOnly="true" type="number"
	value="${(itemsTotal+itemsPerPage-1)/itemsPerPage}" />


<c:url var="url" value="${action.concat('?') }">
	<c:forEach var="entry" items="${param}">
		<c:if test="${entry.key != 'currentPage'}">
			<c:param name="${entry.key}" value="${entry.value}" />
		</c:if>
	</c:forEach>
</c:url>

<div class="center">
	<ul class="pagination">
		<c:if test="${pagesTotal > 1}">
			<c:forEach var="i" begin="1" end="${pagesTotal}">
				<c:set var="currentPage" value="&currentPage=${i}" />
				<li><a
					<c:if test="${param.currentPage==i}">class="active"</c:if>
					href="<c:url value='${url.concat(currentPage)}'/>">${i}</a></li>
			</c:forEach>
		</c:if>
	</ul>
</div>