<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>

<fmt:message key="msg.name" var="msgNameValue" />
<fmt:message key="msg.email" var="msgEmailValue" />
<fmt:message key="msg.date" var="msgDateValue" />
<fmt:message key="button.delete" var="msgBtnDeleteValue" />
<fmt:message key="title.message" var="msgTitleValue" />
<fmt:message key="button.reply" var="msgBtnReplyValue" />


<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/acc_menu.jspf"%>
<article>
	<h2>
		<span>${msgTitleValue}</span>
	</h2>
	<div class="singlemessage">
		<p>
			${msgNameValue}: <span class="msgitem">${singlemsg.name}</span>
		</p>
		<p>${msgEmailValue}:${singlemsg.email}</p>
		<p>${msgDateValue}:${singlemsg.creationDate}</p>
		<br>
		<p>${singlemsg.msg}</p>
		<br> <a target="_blank"
			href="attachments?dirId=${singlemsg.idMsg}"><img
			style="width: 10%; height: 10%;"
			src="attachments?dirId=${singlemsg.idMsg}"></a> <br> <br>

		
		<a href="msgreplaying?email=${singlemsg.email}" class="button"
			id="rebtn">${msgBtnReplyValue}</a>


	</div>
</article>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>