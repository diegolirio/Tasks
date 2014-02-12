<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
		<form action="task_form" class="well form-horizontal" id="id_form_task" method="post" accept-charset="utf-8">
			<div style="display:none;">
				<input type="hidden" name="id" value="${task.id}"/>
				<input type="hidden" name="usuario.id" value="1"/>
			</div>	
			<div class="form-group">
				<label for="id_title" class="col col-md-3 control-label">Title</label>
				<div class="col col-md-3">
					<input name="title" class="form-control" placeholder="Title" maxlength="255" type="text" id="id_title"/>
				</div>
			</div>	
			<div class="form-group">
				<label for="id_completed" class="col col-md-3 control-label">Completed</label>
				<div class="col col-md-1">
					<input name="completed" class="form-control"  type="checkbox" id="id_completed"/>
				</div>
			</div>	
			<div class="form-group">
				<div class="col col-md-9 col-md-offset-3">
					<input  class="btn btn-default" type="submit" value="Sign in"/>
				</div>	
			</div>
		</form>	
	</div>	
</body>
</html>