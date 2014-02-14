<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Task</title>
	<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/bootstrap/css/bootstrap.css" rel="stylesheet">	
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="container">
	
		<h3 class="text-primary">
			<c:if test="${task.id <= 0}">
				<fmt:message key="task.create.new.task"/>
			</c:if>
			<c:if test="${task.id > 0}">
				<fmt:message key="task.alter.task"/>
			</c:if>			
		</h3>
	
		<form action="" class="well form-horizontal" id="id_form_task" method="post" accept-charset="utf-8">
			<div style="display:none;">
				<input type="hidden" name="id" value="${task.id}"/>
				<input type="hidden" name="usuario.id" value="1"/>
			</div>	
			<div class="form-group">
				<label for="id_title" class="col col-md-3 control-label">Title</label>
				<form:errors path="task.title" cssStyle="color:red"/>
				<div class="col col-md-3">
					<input name="title" class="form-control" placeholder="Title" maxlength="20" value="${task.title}" type="text" id="id_title"/>
				</div>
			</div>	
			<div class="form-group">
				<label for="id_completed" class="col col-md-3 control-label">Completed</label>
				<div class="col col-md-1">
					${task.completed} <!-- <input name="completed" class="form-control"  type="checkbox" id="id_completed" value="${task.completed}"/>  -->
				</div>
			</div>
			<div class="form-group">
				<div class="col col-md-9 col-md-offset-3">
					<input  class="btn btn-success" type="submit" value='<fmt:message key="task.button.save"/>'/>
					<a href="/tasks"  class="btn btn-default"><fmt:message key="task.button.cancel"/></a>
				</div>
			</div>
			
		</form>	
	</div>	
	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="static/bootstrap/js/bootstrap.min.js"></script>

	
</body>
</html>