<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="key" required="true"%>




<c:if test="${not empty key}">
	<fmt:message key="${key}" />
</c:if>