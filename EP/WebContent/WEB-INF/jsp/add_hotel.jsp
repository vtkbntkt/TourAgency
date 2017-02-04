<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="title.addhotel" var="titleValue" />
<fmt:message key="placeholder.hotelName" var="hotelNameValue" />
<fmt:message key="placeholder.hotelCountry" var="hotelCountryValue" />
<fmt:message key="placeholder.hotelCity" var="hotelCityValue" />
<fmt:message key="placeholder.hotelDesc" var="hotelDescValue" />
<fmt:message key="button.add" var="btnAddValue" />
<fmt:message key="button.upload" var="btnUpValue" />
<fmt:message key="tip.file" var="tipFileValue" />
<fmt:message key="tip.remsg" var="tipDescValue" />

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
		<form action="hotel" method="post" enctype="multipart/form-data">
		     <span class="succmsg"><ep:tip key="${hotelAdding}" /></span> 
		     <span class="errormsg"><ep:tip key="${addhotelErrors['addhotel.error.msg']}" /></span>
		
		
			<input type="text" name="hotelName" value="${hotelForm.name}"  required placeholder="${hotelNameValue}">
			<div class="errormsg"><ep:tip key="${addhotelErrors['hotelName']}" /></div>
			
			
			<input id = "hotelRate" style="width:20%" type="number" name="hotelRate" value="${hotelForm.rating}" min="2" max="5" required  >
			<span id ="starCount" style="color:orange"></span>
			<div class="errormsg"><ep:tip key="${addhotelErrors['hotelRate']}" /></div>
			
			
			<input type="text" name="hotelCountry" value="${hotelForm.country}"  placeholder="${hotelCountryValue}" required >
			<span class="errormsg"><ep:tip key="${addhotelErrors['hotelCountry']}" /></span>
			 
			
			<input type="text" name="hotelCity" value="${hotelForm.city}" placeholder="${hotelCityValue}" required > 
			<span class="errormsg"><ep:tip key="${addhotelErrors['hotelCity']}" /></span>

			<div class="tooltip">
			<textarea id="retext" name="description" maxlength="500" placeholder="${hotelDescValue}" required>${hotelForm.description}</textarea>
			<span class="errormsg"><ep:tip key="${addhotelErrors['description']}" /></span>
			<span class="tooltiptext">${tipDescValue}</span> 
			<span id="counterRe"></span> 
			</div>
			<div class="tooltip">
				<p class="file"> <input type="file" name="file" id="file" multiple /> 
				<label for="file">${btnUpValue}</label>
				</p>
				<span class="tooltiptext">${tipFileValue}</span> 
				<span class="errormsg"><ep:tip key="${addhotelErrors['file']}" /></span>
			</div>
			
			<input class="button" type="submit" value="${btnAddValue}">
		</form>
	</div>
</article>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>