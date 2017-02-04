<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ attribute name="val" required="true" rtexprvalue="true"
	type="java.lang.Double"%>

<fmt:parseNumber var="cost" integerOnly="true" type="number"
	value="${val*sessionScope.currentRate}" />
<c:out value="${cost} ${fn:toUpperCase(sessionScope.currency)}" />






