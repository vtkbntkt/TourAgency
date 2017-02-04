<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ep" uri="/WEB-INF/custom.tld"%>



<fmt:message key="placeholder.captcha" var="captchaPlaceValue" />

<div class="captcha">
	<img id="captchaId" class="captchaImg" src="captcha.jpg"> <input
		type="text" name="captchaValue" value="${captchaPlaceValue}">
	<span class="errormsg"><ep:tip
			key="${loginErrors['captcha.error.value']}" /></span> <br>
	<span class="errormsg"><ep:tip
			key="${loginErrors['captcha.error.time']}" /></span>
</div>
