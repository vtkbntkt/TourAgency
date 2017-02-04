<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="button.update" var="btnUpdateValue" />
<fmt:message key="title.updatecurrency" var="titleUpdateValue" />


<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />

<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/acc_menu.jspf"%>
<article>
	<h2>
		<span>${titleUpdateValue }</span>
	</h2>


	<div class="standartform">
		<form action="edit_currency" method="post">
			<div class="succmsg"><ep:tip key="${rateUpdate}" /></div> 
			<div class="errormsg"><ep:tip key="${editcurrErrors['editcurr.error.msg']}" /></div>
					
		   <select style="width: 30%" name="code" id="bank_kode">
				<c:forEach var="currency" items="${currencyList}">
					<option value="${currency.bc.name}" data-value-type="${currency.rate}" 
					${currency.bc.name == currEditForm.bc ? 'selected' : ''}>${currency.bc}</option>
				</c:forEach>
			</select> 
			
			<input style="width: 30%" type="number" name="rate" id="rate_value" value="" min="0.01" step="0.01" required> 	
			<input class="button" type="submit" value="${btnUpdateValue}">
			<div class="errormsg"> <ep:tip key="${editcurrErrors['code']}" /> </div>
			<div class="errormsg"> <ep:tip key="${editcurrErrors['rate']}" /> </div>
		</form>
	</div>
</article>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>