<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="msg.emptylist" var="emptylistValue" />
<fmt:message key="filter.display" var="displayValue" />

<fmt:message key="user.email" var="usersEmailValue" />
<fmt:message key="user.lastname" var="usersLNValue" />
<fmt:message key="user.firstname" var="usersFNValue" />
<fmt:message key="user.role" var="usersRoleValue" />
<fmt:message key="user.status" var="usersStatusValue" />
<fmt:message key="title.users" var="usersTitleValue" />

<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/acc_menu.jspf"%>
<article>
	<h2>
		<span>${usersTitleValue}</span>
	</h2>
	<div class="filter">
		<form action="users">
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


	<table class="mytable">
		<tr>
			<th>ID</th>
			<th>${usersEmailValue }</th>
			<th>${usersFNValue }</th>
			<th>${usersLNValue}</th>
			<th>${usersRoleValue }</th>
			<th>${usersStatusValue }</th>

		</tr>
		<c:choose>
			<c:when test="${not empty userList}">

				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.id}</td>
						<td><a target="_blank"
							href="msgreplaying?email=${user.email}">${user.email}</a></td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td><fmt:message key="${user.role}" /></td>
						<td>
							<form action="user_status" method="post">
								<input type="hidden" name="userId" value="${user.id}" />
								<button type="submit" class="minibtn">
									<fmt:message key="${user.status}" />
								</button>
							</form>
						</td>

					</tr>
				</c:forEach>

			</c:when>
			<c:otherwise>
				<div>
					<span class="errormsg">${emptylistValue}</span>
				</div>
			</c:otherwise>
		</c:choose>
	</table>
	<br>
	<ep:pagination itemsTotal="${itemsTotal}" action="users"
		itemsPerPage="${param.itemsPerPage.matches('[0-9]+')?param.itemsPerPage:10}" />
</article>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>