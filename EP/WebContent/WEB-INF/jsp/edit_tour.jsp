<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="title.edittour" var="titleValue" />
<fmt:message key="button.update" var="btnUpdateValue" />
<fmt:message key="button.changeimage" var="btnChangeImgValue" />
<fmt:message key="tip.file" var="tipFileValue" />
<fmt:message key="placeholder.cost" var="costValue" />
<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />

<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/acc_menu.jspf"%>
<article>
	<h2>
		<span>${titleValue}</span>
	</h2>


	<div class="standartform">
		<form action="edit_tour" method="post" enctype="multipart/form-data">
			<span class="errormsg"><ep:tip
					key="${edittourErrors['edittour.error.msg']}" /></span> <input
				type="hidden" name="tourId" value="${tourEditForm.id}" />
			<div class="errormsg">
				<ep:tip key="${edittourErrors['tourId']}" />
			</div>
			<div id="image-holder">
				<img style="width: 160px; height: 120px;"
					src="tour_photos?dirId=${tourEditForm.id}">
			</div>
			<div class="tooltip">
				<p class="file">
					<input type="file" name="file" id="file" /> <label for="file">${btnChangeImgValue}</label>
				</p>
				<span class="tooltiptext">${tipFileValue}</span> <span
					class="errormsg"><ep:tip key="${edittourErrors['file']}" /></span>
			</div>


			<select name="tourtype">
				<c:forEach var="type" items="${typeList}">
					<option value="${type.id}"
						${tourEditForm.type == type.id ? 'selected' : ''}><fmt:message
							key="${type.name}" /></option>
				</c:forEach>
			</select>
			<div class="errormsg">
				<ep:tip key="${edittourErrors['tourtype']}" />
			</div>

			<input type="text" name="depdate" value="${tourEditForm.date}"
				placeholder="2017-02-04" required
				pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])">
			<div class="errormsg">
				<ep:tip key="${edittourErrors['depdate']}" />
			</div>

			<input id="nightNum" style="width: 20%" type="number" name="nightNum"
				value="${tourEditForm.nightNum}" min="1" max="14" required>
			<span id="nightCount" style="color: orange"></span>
			<div class="errormsg">
				<ep:tip key="${edittourErrors['nightNum']}" />
			</div>


			<input style="width: 95%" type="text" name="cost"
				value="${tourEditForm.cost}" placeholder="${costValue}" required
				pattern="^[1-9]\d*$"> <span
				style="color: orange; font-size: 20px;">$</span>
			<div class="errormsg">
				<ep:tip key="${edittourErrors['cost']}" />
			</div>


			<input id="peopleNum" style="width: 20%" type="number"
				name="peopleNum" value="${tourEditForm.personNum}" min="1" max="5"
				required> <span id="peopleCount"
				style="color: orange; font-size: 20px;"></span>
			<div class="errormsg">
				<ep:tip key="${edittourErrors['peopleNum']}" />
			</div>

			<select name="hotel">
				<c:forEach var="hotel" items="${hotelList}">
					<option value="${hotel.id}"
						${tourEditForm.hotel == hotel.id ? 'selected' : ''}>
						${hotel.name}${hotel.raiting}*, ${hotel.country} ${hotel.city}</option>
				</c:forEach>
			</select>
			<div class="errormsg">
				<ep:tip key="${edittourErrors['hotel']}" />
			</div>




			<input class="button" type="submit" value="${btnUpdateValue}">
		</form>
	</div>
</article>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>