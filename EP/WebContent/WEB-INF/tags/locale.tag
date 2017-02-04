<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form style="display: inline;" action="language">
<select id="langOption" name="lang" onchange="submit()">
	<option value="ru" ${sessionScope.language == 'ru' ? 'selected' : ''}>RU</option>
	<option value="en" ${sessionScope.language == 'en' ? 'selected' : ''}>ENG</option>
</select>
</form>





