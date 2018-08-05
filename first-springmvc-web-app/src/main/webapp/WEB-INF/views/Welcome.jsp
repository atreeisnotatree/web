<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<spring:message code="welcome.caption"/> ${name } <br>
	Now, you can <a href="/list-todos"> manage your todo's.</a>
</div>

<%@ include file="common/footer.jspf"%>