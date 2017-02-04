<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="title.contactus" var="titleValue" />
<fmt:message key="placeholder.name" var="nameValue" />
<fmt:message key="placeholder.email" var="emailValue" />
<fmt:message key="placeholder.msg" var="msgValue" />
<fmt:message key="button.upload" var="btnUpValue" />
<fmt:message key="button.send" var="btnSendValue" />
<fmt:message key="tip.name" var="tipNameValue" />
<fmt:message key="tip.file" var="tipFileValue" />
<fmt:message key="tip.msg" var="tipMsgValue" />

<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>


<div class="signform">


	<h2>
		<span>${titleValue}</span>
	</h2>
	<form action="contactus" method="post" enctype="multipart/form-data">

		<div class="tooltip">
			<span class="succmsg"><ep:tip key="${contactUsSending}" /></span> <span
				class="errormsg"><ep:tip key="${contactUsErrors['notice']}" /></span>
			<%-- comment --%>

			<input type="text" name="name" required value="${contactForm.name}"
				pattern="^[A-ZА-Я][a-zа-я]{1,19}$" placeholder="${nameValue}">
			<span class="tooltiptext">${tipNameValue}</span> <span
				class="errormsg"><ep:tip key="${contactUsErrors['name']}" /></span>
		</div>

		<div class="tooltip">
			<input type="email" name="email" required placeholder="${emailValue}"
				value="${contactForm.email}"> <span class="errormsg"><ep:tip
					key="${contactUsErrors['email']}" /></span>
		</div>

		<div class="tooltip">
			<textarea name="msg" maxlength="200" required
				placeholder="${msgValue}" id="msgTextarea">${contactForm.message}</textarea>
			<span class="tooltiptext">${tipMsgValue}</span> <span id="counter"></span>
			<span class="errormsg"><ep:tip key="${contactUsErrors['msg']}" /></span>
		</div>
		<div id="image-holder">
			</div>

		<div class="tooltip">
			<p class="file">
				<input type="file" name="file" id="file" /> <label for="file">${btnUpValue}</label>
			</p>
			<span class="tooltiptext">${tipFileValue}</span> <span
				class="errormsg"><ep:tip key="${contactUsErrors['file']}" /></span>
		</div>

		<br> <input type="submit" value="${btnSendValue}">
	</form>
</div>


<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>