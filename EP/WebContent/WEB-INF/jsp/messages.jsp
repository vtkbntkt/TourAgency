<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="msg.emptylist" var="emptylistValue" />
<fmt:message key="filter.display" var="displayValue" />

<fmt:message key="messages.name" var="msgsNameValue" />
<fmt:message key="messages.date" var="msgsDateValue" />
<fmt:message key="messages.fragment" var="msgsFragmentValue" />
<fmt:message key="title.messages" var="msgsTitleValue" />
<fmt:message key="button.delete" var="msgsBtnDeleteValue" />


<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/acc_menu.jspf"%>
<article>
	<h2>
		<span>${msgsTitleValue }</span>
	</h2>

	<div class="filter">
		<form action="messages">
			<div class="tooltipf">
				<select name="itemsPerPage" onchange="submit()">
					<option value="10" ${param.itemsPerPage == '10'? 'selected' : ''}>10</option>
					<option value="15" ${param.itemsPerPage == '15'? 'selected' : ''}>15</option>
					<option value="20" ${param.itemsPerPage == '20'? 'selected' : ''}>20</option>
				</select> <span class="tooltiptext">${displayValue}</span>
			</div>
		</form>
	</div>
	<br>




	<c:choose>
		<c:when test="${not empty messageList}">
			<table class="mytable">
				<tr>
					<th>${msgsNameValue }</th>
					<th>${msgsFragmentValue }</th>
					<th>${msgsDateValue }</th>
					<th>

						<form action="message_remove" method="post" id="deleteForm">
							<input type="hidden" name="delIds"  id ="delIds"/>
							<button  type="submit" class="minibtn">${msgsBtnDeleteValue }
							</button>
						</form></th>
				</tr>
				<c:forEach items="${messageList}" var="message">
					<tr>
						<td>${message.name}</td>
						<td><a href="message?idmsg=${message.idMsg}">${fn:substring(message.msg, 0, 30)}</a></td>
						<td>${message.creationDate}</td>
						<td><input type="checkbox" name="chb"
							value="${message.idMsg}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div>
				<span class="errormsg">${emptylistValue}</span>
			</div>
		</c:otherwise>
	</c:choose>
	<br>
	<ep:pagination itemsTotal="${itemsTotal}" action="messages"
		itemsPerPage="${param.itemsPerPage.matches('[0-9]+')?param.itemsPerPage:10}" />
</article>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>