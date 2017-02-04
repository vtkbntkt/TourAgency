<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf"%>


<fmt:message key="tip.remsg" var="tipReMsgValue" />
<fmt:message key="button.send" var="btnSendValue" />
<fmt:message key="placeholder.msg" var="reMsgValue" />
<fmt:message key="title.remsg" var="reMsgTitleValue" />

<!DOCTYPE html>
<html>
<c:set var="title" value="My project" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/nav.jspf"%>
<%@ include file="/WEB-INF/jspf/acc_menu.jspf"%>
<article>
	<h2>
		<span>${reMsgTitleValue}</span>
	</h2>
	
	<div class="standartform">
		<form action="msgreplaying" method="post">
	
		     <span class="succmsg"><ep:tip key="${reSending}" /></span> 
		     <span class="errormsg"><ep:tip key="${replayingErrors['notice']}" /></span>
		
		
			<input type="text" name="msgemail" value="${not empty reForm.email ? reForm.email : param.email}"  required>
			<span class="errormsg"><ep:tip key="${replayingErrors['msgemail']}" /></span>
			
			
			<input type="text" name="msgsubject" value="${not empty reForm.subject ? reForm.subject : 'RE:'}"required >
			<span class="errormsg"><ep:tip key="${replayingErrors['msgsubject']}" /></span>
			 

			<div class="tooltip">
			<textarea id="retext" name="msg" maxlength="500" placeholder="${reMsgValue}" required>${reForm.message}</textarea>
			<span class="errormsg"><ep:tip key="${replayingErrors['msg']}" /></span>
			<span class="tooltiptext">${tipReMsgValue}</span> 
			<span id="counterRe"></span> 
			</div>
			
			<input class="button" type="submit" value="${btnSendValue}">
		</form>
	</div>
	



	
</article>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>