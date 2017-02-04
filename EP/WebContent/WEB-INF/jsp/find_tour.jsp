<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>


<fmt:message key="msg.emptylist" var="emptylistValue" />
<fmt:message key="button.search" var="btnSearchValue" />
<fmt:message key="filter.costFrom" var="costFromValue" />
<fmt:message key="filter.costTo" var="costToValue" />
<fmt:message key="filter.person" var="personNumValue" />
<fmt:message key="filter.rating" var="hotelRatingValue" />
<fmt:message key="filter.nights" var="nightNumValue" />
<fmt:message key="filter.date" var="depDateValue" />
<fmt:message key="filter.sorting" var="sortingValue" />
<fmt:message key="filter.cost" var="costValue" />
<fmt:message key="filter.display" var="displayValue" />
<fmt:message key="tour.date" var="tourDateValue" />
<fmt:message key="tour.nights" var="tourNightValue" />
<fmt:message key="tour.cost" var="tourCostValue" />
<fmt:message key="tour.type" var="tourTypeValue" />
<fmt:message key="tour.loc" var="tourLocValue" />
<fmt:message key="tour.persons" var="tourPersonsValue" />
<fmt:message key="tour.menu.more" var="moreValue" />
<fmt:message key="tour.menu.change" var="changeValue" />
<fmt:message key="tour.menu.remove" var="removeValue" />
<fmt:message key="tour.menu.order" var="orderValue" />

<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/services.jspf"%>



<article>
	<h2>
		<span>${searchValue}</span>
	</h2>
	<div class="filter">
		<form id="tourFilter" action="tours" method="get">
			<div class="tooltipf">
				<select name="country">
					<option value="" ${empty param.country ? 'selected' : ''}></option>
					<c:forEach var="country" items="${countryValues}">
						<option value="${country}"
							${param.country == country ? 'selected' : ''}>${country}</option>
					</c:forEach>
				</select>
			</div>
			<div class="tooltipf">
				<select name="type">
					<option value="" ${empty param.type ? 'selected' : ''}></option>
					<c:forEach var="type" items="${typeValues}">
						<option value="${type}" ${param.type == type ? 'selected' : ''}><fmt:message
								key="${type}" />
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="tooltipf">
				<input type="text" name="costFrom" value="${param.costFrom}"
					pattern="\d*" size="2"><span class="tooltiptext">${costFromValue},
					${sessionScope.currency}</span>
			</div>
			<div class="tooltipf">
				<input type="text" name="costTo" value="${param.costTo}"
					pattern="\d*" size="2"> <span class="tooltiptext">${costToValue},
					${sessionScope.currency}</span>
			</div>
			<div class="tooltipf">
				<select name="person_num">
					<option value="" ${empty param.person_num ? 'selected' : ''}></option>
					<c:forEach var="person" items="${personValues}">
						<option value="${person}"
							${param.person_num eq person ? 'selected' : ''}>${person}</option>
					</c:forEach>
				</select><span class="tooltiptext">${personNumValue}</span>
			</div>
			<div class="tooltipf">
				<select name="rating">
					<option value="" ${empty param.rating ? 'selected' : ''}></option>
					<c:forEach var="rate" items="${ratingValues}">
						<option value="${rate}" ${param.rating == rate ? 'selected' : ''}>${rate}</option>
					</c:forEach>
				</select> <span class="tooltiptext">${hotelRatingValue}</span>
			</div>
			<div class="tooltipf">
				<select name="night_num">
					<option value="" ${empty param.night_num ? 'selected' : ''}></option>
					<c:forEach var="night" items="${nightValues}">
						<option value="${night}"
							${param.night_num == night ? 'selected' : ''}>${night}</option>
					</c:forEach>
				</select><span class="tooltiptext">${nightNumValue}</span>
			</div>
			<div class="tooltipf">
				<select name="dept_date">
					<option value="" ${empty param.dept_date ? 'selected' : ''}></option>
					<c:forEach var="date" items="${dateValues}">
						<option value="${date}"
							${param.dept_date == date ? 'selected' : ''}>${ date}</option>
					</c:forEach>
				</select> <span class="tooltiptext">${depDateValue}</span>
			</div>
			<div class="tooltipf">
				<select name="sorting">
					<option value="" ${empty param.sorting? 'selected' : ''}></option>
					<option value="cost" ${param.sorting == 'cost'? 'selected' : ''}>${costValue}</option>
					<option value="rating"
						${param.sorting == 'rating'? 'selected' : ''}>${hotelRatingValue}</option>
					<option value="date" ${param.sorting == 'date'? 'selected' : ''}>${depDateValue}</option>
				</select> <span class="tooltiptext">${sortingValue}</span>
			</div>
			<div class="tooltipf">
				<select name="itemsPerPage">
					<option value="10" ${param.itemsPerPage == '10'? 'selected' : ''}>10</option>
					<option value="15" ${param.itemsPerPage == '15'? 'selected' : ''}>15</option>
					<option value="20" ${param.itemsPerPage == '20'? 'selected' : ''}>20</option>
				</select> <span class="tooltiptext">${displayValue}</span>
			</div>
			<input type="submit" value="${btnSearchValue}">
		</form>
	</div>

	<c:choose>
		<c:when test="${not empty tourList}">
			<c:forEach items="${tourList}" var="tour">
				<div class=tours id="tours">
					<div class="hotelName" style="color: green; font-size: 20px;">
						${tour.hotelName} <span style="color: orange; font-size: 20px;">
							${tour.hotelRating}&#x2605;</span>

						<c:if test="${tour.isHot}">
							<span class="font-effect-fire"
								style="color:; font-size: 20px; float: right;"> H O T </span>
						</c:if>

					</div>
					<img src="tour_photos?dirId=${tour.tourId}">
					<ul>
						<li>${tourDateValue}</li>
						<li>${tourCostValue}</li>
						<li>${tourLocValue}</li>
						<li>${tourTypeValue }</li>
						<li>${tourPersonsValue}</li>
					</ul>
					<ul id="tourValues">
						<li>${tour.departureDate},${tour.nights}${tourNightValue}</li>

						<li><ep:cost val="${tour.price}" /></li>
						<li>${tour.country},${tour.city}</li>
						<li><fmt:message key="${tour.type.name}" /></li>
						<li>${tour.personCount}</li>
					</ul>
					<ul id="tourOption">
						<li><form target="_blank" action="tour" method="get">
								<input type="hidden" name="tourId" value="${tour.tourId}" />
								<button type="submit" class="minibtn">${moreValue}</button>
							</form></li>
						<c:choose>
							<c:when test="${sessionScope.user.roleId == 0}">
								<li><form action="remove_tour" method="post">
										<input type="hidden" name="tourId" value="${tour.tourId}" />
										<button type="submit" class="minibtn">${removeValue}</button>
									</form></li>
								<li><form action="tour_status" method="post">
										<input type="hidden" name="tourId" value="${tour.tourId}" />
										<button type="submit" class="minibtn">${changeValue}</button>
									</form></li>
							</c:when>
							<c:when test="${sessionScope.user.roleId == 2}">
								<li><form action="tour_status" method="post">
										<input type="hidden" name="tourId" value="${tour.tourId}" />
										<button type="submit" class="minibtn">${changeValue}</button>
									</form></li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${sessionScope.user.roleId == 1}">
										<li><form action="order_tour" method="post">
												<input type="hidden" name="tourId" value="${tour.tourId}" />
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
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>
				<span class="errormsg">${emptylistValue}</span>
			</div>
		</c:otherwise>
	</c:choose>

	<ep:pagination itemsTotal="${itemsTotal}" action="tours"
		itemsPerPage="${param.itemsPerPage.matches('[0-9]+')?param.itemsPerPage:10}" />
</article>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
