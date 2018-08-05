
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
<h1>Add a todo</h1>
<form:form method="post" commandName="todo">

	<form:hidden path="id"/>
	<form:hidden path="user"/>
	
	<fieldset class="form-group">
	<form:label path="desc">Description</form:label>
	<form:input path="desc" type="text" class="form-control" required="required"/>
	<form:errors path="desc" cssClass="text-Warning"/>
	</fieldset>
	
	<fieldset class="form-group">
	<form:label path="targetDate">Target Date</form:label>
	<form:input path="targetDate" type="text" class="form-control" required="required"/>
	<form:errors path="targetDate" cssClass="text-Warning"/>
	</fieldset>
	
	<input class="btn btn-success" value="Add" type="submit"/>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>