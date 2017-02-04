<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="button.update" var="btnUpdateValue" />
<fmt:message key="tip.tourdiscount" var="tipTourDiscValue" />
<fmt:message key="tip.maxdiscount" var="tipMaxDiscValue" />
<fmt:message key="title.updatediscount" var="titleUpdateDiscValue" />



<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />

<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/acc_menu.jspf"%>
<article>
	<h2>
		<span>${titleUpdateDiscValue}</span>
	</h2>


	<div class="standartform">
		<form action="edit_discount" method="post">
			<div class="succmsg">
				<ep:tip key="${discUpdate}" />
			</div>
			<div class="errormsg">
				<ep:tip key="${editdiscErrors['editdisc.error.msg']}" />
			</div>

			<span class="tooltip"> <input style="width: 30%" type="number"
				name="discountPerTour" value="${currentDiscount.percentPerTour}" 
				min="0.00" step="0.01" required>
				<span class="tooltiptext">${tipTourDiscValue}</span>
			</span> 
			
			
			<span class="tooltip"> <input style="width: 30%"
				type="number" name="maxDiscount" value="${currentDiscount.maxPercent}" 
				min="0.00" step="0.01" required> <span class="tooltiptext">${tipMaxDiscValue}</span>
			</span> 
			
			<input class="button" type="submit" value="${btnUpdateValue}">
			
			<div class="errormsg">
				<ep:tip key="${editdiscErrors['discountPerTour']}" />
			</div>
			<div class="errormsg">
				<ep:tip key="${editdiscErrors['maxDiscount']}" />
			</div>
		</form>
	</div>
</article>
</body>
</html>