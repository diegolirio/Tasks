<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Delete Item ?</title>
	<link href="../../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="../../static/bootstrap/css/bootstrap.css" rel="stylesheet">	
</head>
<body>
	
	<div class="input-group">
		<div class="container">
			<form action="" method="POST">
				<h1 class="text-primary"><fmt:message key="task.we.really.want.to.delete.the.task.item"/></h1>
				<br/>
				<h4>ID: ${item.id}</h4>
				<h3>Description: ${item.description}</h3>
				<br/>
				<input type="hidden" value="${item.id}" name="id">			
				<input type="submit" value='<fmt:message key="task.button.confirm" />' class="btn btn-success">
				<a href="#" onclick="close_modal('X');" class="btn btn-default"><fmt:message key="task.button.cancel" /></a>
			</form>
		</div>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="static/bootstrap/js/bootstrap.min.js"></script>
    
	<script type="text/javascript">		
	
		if ( '${status}' == 'N' ) {
			alert('${message}');
			close_modal('N');
		}

		function close_modal(status) {	
			if (status == 'N') {			
				window.opener.location.reload();
				//window.opener.location.replace(window.opener.location); 
			}				
			window.close();				                
		}
	</script>    
    
</body>
</html>