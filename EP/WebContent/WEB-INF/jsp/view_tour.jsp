<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>


<fmt:message key="tour.date" var="tourDateValue" />
<fmt:message key="tour.nights" var="tourNightValue" />
<fmt:message key="tour.cost" var="tourCostValue" />
<fmt:message key="tour.type" var="tourTypeValue" />
<fmt:message key="tour.loc" var="tourLocValue" />
<fmt:message key="tour.persons" var="tourPersonsValue" />
<fmt:message key="tour.title" var="tourTitleValue" />
<fmt:message key="tour.menu.edit" var="editValue" />
<fmt:message key="tour.menu.order" var="orderValue" />


<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/services.jspf"%>

<article>
	<h2>
		<span>${tourTitleValue}</span>
	</h2>

	<div class=tours style="border: none">
		<div id="slideshow">
			<c:forEach items="${images}" var="image">
				<div>
					<img
						src="hotel_photos?filename=${image}&dirId=${singleTour.hotelId}">
				</div>
			</c:forEach>
		</div>
		<br>
		<div class="hotelName" style="color: green; font-size: 20px;">
			${singleTour.hotelName}<span id="starNum"
				style="color: orange; font-size: 20px;"> </span>
			<c:if test="${singleTour.isHot}">
				<span class="font-effect-fire"
					style="color:; font-size: 20px; float:;"> H O T </span>
			</c:if>
		</div>
		<script>
			getRating('starNum', '${singleTour.hotelRating}');
		</script>
		<ul style="margin-left: 0px;">
			<li>${tourDateValue}</li>
			<li>${tourCostValue}</li>
			<li>${tourLocValue}</li>
			<li>${tourTypeValue }</li>
			<li>${tourPersonsValue}</li>
		</ul>
		<ul id="tourValues">
			<li>${singleTour.departureDate},${singleTour.nights}
				${tourNightValue}</li>
			<li><ep:cost val="${singleTour.price }" /></li>
			<li>${singleTour.country},${singleTour.city}</li>
			<li><fmt:message key="${singleTour.type.name}" /></li>
			<li>${singleTour.personCount}</li>
		</ul>

		<ul id="tourOption">
			<c:choose>
				<c:when test="${sessionScope.user.roleId == 0}">
					<li><form action="edit_tour" method="get">
							<input type="hidden" name="tourId" value="${singleTour.tourId}" />
							<button type="submit" class="minibtn">${editValue}</button>
						</form></li>
				</c:when>
				<c:when test="${sessionScope.user.roleId == 2}">
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${sessionScope.user.roleId == 1}">
							<li><form action="order_tour" method="post">
									<input type="hidden" name="tourId" value="${singleTour.tourId}" />
									<button type="submit" class="minibtn">${orderValue}</button>
								</form></li>
						</c:when>
						<c:otherwise>
							<li><form action="login" method="get">
									<button type="submit" class="minibtn">${orderValue}</button>
								</form></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</ul>
		<p>${singleTour.description}</p>
	</div>

</article>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>