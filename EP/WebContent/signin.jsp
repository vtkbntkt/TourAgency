<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="title.login" var="titleValue" />
<fmt:message key="placeholder.username" var="usernameValue" />
<fmt:message key="placeholder.pwd" var="pwdValue" />
<fmt:message key="button.enter" var="btnEnterValue" />




<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>

<div class="signform">
	<h2>
		<span>${titleValue}</span>
	</h2>
	<form action="login" method="post">
		<span class="errormsg"><ep:tip
				key="${loginErrors['login.error.msg']}" /></span> <input type="text"
			name="username" value="${loginForm.username}"
			placeholder="${usernameValue}"> <span class="errormsg"><ep:tip
				key="${loginErrors['username']}" /></span> <input type="password"
			name="password" placeholder="${pwdValue}"> <span
			class="errormsg"><ep:tip key="${loginErrors['password']}" /></span>
		<ep:captcha />
		<input type="submit" value="${btnEnterValue}">
	</form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>