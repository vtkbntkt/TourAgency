<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form style="display: inline;" action="currency">
	<select id="currencyOption" name="currencyOption" onchange="submit()">
		<option value="usd" ${sessionScope.currency == 'usd' ? 'selected' : ''}>USD</option>
		<option value="eur" ${sessionScope.currency == 'eur' ? 'selected' : ''}>EUR</option>
		<option value="uah" ${sessionScope.currency == 'uah' ? 'selected' : ''}>UAH</option>
	</select>
</form>



