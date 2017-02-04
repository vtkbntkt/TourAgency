<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="title.registration" var="titleValue" />
<fmt:message key="placeholder.email" var="emailValue" />
<fmt:message key="placeholder.firstName" var="firstNameValue" />
<fmt:message key="placeholder.lastName" var="lastNameValue" />
<fmt:message key="placeholder.pwd" var="pwdValue" />
<fmt:message key="placeholder.repwd" var="rePwdValue" />
<fmt:message key="tip.name" var="tipNameValue" />
<fmt:message key="tip.pwd" var="tipPwdValue" />
<fmt:message key="button.register" var="btnRegisterValue" />

<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>

<div class="signform">
	<h2>
		<span>${titleValue}</span>
	</h2>

	<form action="register" method="post">
		<div class="tooltip">
			<span class="errormsg"><ep:tip
					key="${registerErrors['register.error.msg']}" /></span> <input
				type="text" name="firstname" value="${registerForm.firstName}" required
				pattern="^[A-ZА-Я][a-zа-я]{1,19}$" placeholder="${firstNameValue}">
			<span class="tooltiptext">${tipNameValue}</span> <span
				class="errormsg"><ep:tip key="${registerErrors['firstname']}" /></span>
		</div>

		<div class="tooltip">
			<input type="text" name="lastname" value="${registerForm.lastName}" required
				pattern="^[A-ZА-Я][a-zа-я]{1,19}$" placeholder="${lastNameValue}">
			<span class="tooltiptext">${tipNameValue}</span> <span
				class="errormsg"><ep:tip key="${registerErrors['lastname']}" /></span>
		</div>
		<div class="tooltip">
			<input type="email" name="email" value="${registerForm.email}" required
				placeholder="${emailValue}"> <span class="tooltiptext">john@mail.com</span>
			<span class="errormsg"><ep:tip
					key="${registerErrors['email']}" /></span>
		</div>

		<div class="tooltip">
			<input type="password" name="pwd" required
				pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$"
				placeholder="${pwdValue}"> <span class="tooltiptext">${tipPwdValue}
			</span> <span class="errormsg"><ep:tip
					key="${registerErrors['pwd']}" /></span>
		</div>

		<div class="tooltip">
			<input type="password" name="repwd" required
				pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$"
				placeholder="${rePwdValue}"> <span class="tooltiptext">${tipPwdValue}</span>
			<span class="errormsg"><ep:tip
					key="${registerErrors['repwd']}" /></span>
		</div>
		<input type="submit" value="${btnRegisterValue}">
	</form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>