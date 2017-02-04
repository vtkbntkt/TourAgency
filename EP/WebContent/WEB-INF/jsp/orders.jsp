<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:message key="msg.emptylist" var="emptylistValue" />
<fmt:message key="filter.display" var="displayValue" />
<fmt:message key="title.orders" var="titleOrdersValue" />
<fmt:message key="order.date" var="orderDateValue" />
<fmt:message key="order.customer" var="orderCustValue" />
<fmt:message key="order.tour" var="orderTourValue" />
<fmt:message key="order.view" var="orderViewValue" />
<fmt:message key="order.cost" var="orderCostValue" />
<fmt:message key="order.details" var="orderDetailsValue" />
<fmt:message key="order.status" var="orderStatusValue" />

<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/acc_menu.jspf"%>
<article>
	<h2>
		<span>${titleOrdersValue }</span>
	</h2>

	<div class="filter">
		<form action="orders">
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
			<th>${orderDateValue }</th>
			<th>${orderCustValue }</th>
			<th>${orderTourValue }</th>
			<th>${orderCostValue }</th>
			<th>${orderDetailsValue }</th>
			<th>${orderStatusValue }</th>
		</tr>
		<c:choose>
			<c:when test="${not empty orderList}">
				<c:forEach items="${orderList}" var="order">

					<tr>
						<td>${order.orderDate}</td>
						<td>${order.firstName} ${order.lastName}</td>
						<td><a target="_blank" href="tour?tourId=${order.tourId}">${orderViewValue }</a></td>
						<td><ep:cost val="${order.totalCost}" /></td>
						<td><a  href="download_details?orderId=${order.id}"><img id="pdf" src="res/img/pdf.png"></a></td>
						<td><c:choose>
								<c:when
									test="${sessionScope.user.roleId == 0 || sessionScope.user.roleId == 2}">
									<form action="order_status" method="post">
									<input type="hidden" name = "orderId" value="${order.id}">
										<div class="tableoption">
											<select name="orderStatus" onchange="submit()">
												<c:forEach var="status" items="${orderStatusList}">
													<option value="${status.id}"
														${order.orderStatus.id == status.id ? 'selected' : ''}>
														<fmt:message key="${status.name}" /></option>
												</c:forEach>
											</select>
										</div>
									</form>
								</c:when>
								<c:otherwise>
									<fmt:message key="${order.orderStatus.name}" />
								</c:otherwise>
							</c:choose></td>
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
	<ep:pagination itemsTotal="${itemsTotal}" action="orders"
		itemsPerPage="${param.itemsPerPage.matches('[0-9]+')?param.itemsPerPage:10}" />
</article>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>