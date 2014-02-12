<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	 <link href="../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	 <link href="../static/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
	
		<div class="panel panel-primary">
			<div class="panel-heading">Tasks</div>
			<div class="panel-body">
			    <p>${task.title}</p>
			</div>
			<!-- Table -->
			<table class="table">
				<c:forEach var="i" items="${task.items}">
				    <tr>			    	
				    	<td>${i.description}</td>
				    	<td><a title="Alterar" href="#"><span class="glyphicon glyphicon-pencil"></span></a></td>
				    	<td><a title="Excluir" href="#"><span class="glyphicon glyphicon-remove"></span></a></td>
				    </tr>
				</c:forEach>			    
			</table>			
		</div>

			
	</div>	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="static/bootstrap/js/bootstrap.min.js"></script>



</body>
</html>
