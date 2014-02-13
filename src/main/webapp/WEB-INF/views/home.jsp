<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	 <link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	 <link href="static/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
	
		
		<div class="panel panel-primary">
			<div class="panel-heading">Tasks <a title="Alterar" href="#"><span class="glyphicon glyphicon-pencil"></span></a></div>
			<div class="panel-body">
				<a href="task_form" class="btn btn-default btn-xs pull-right">
				  <span class="glyphicon glyphicon-plus"></span> Create New Task
				</a>
			    <p>List</p>
			</div>
			<!-- Table -->
			<table class="table">
				<c:forEach var="t" items="${tasks}">
				    <tr>			    	
				    	<td><a href="items/?id=${t.id}&title=${t.title}">${t.title}</a></td>
				    	<td><a title="Alterar" href="task_form?id=${t.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
				    	<td><a title="Excluir" href="task_delete?id=${t.id}" onclick="show_modal(this.href); return false;"><span class="glyphicon glyphicon-remove"></span></a></td>
				    </tr>
				</c:forEach>			    
			</table>			
		</div>
		
		
	</div>
	
	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">				
		function show_modal(url) {
			w = window.open(url, 
							'', 
							'height=550, width=750, top=150, left=250, scrollbars=no, resizable=no');								
		}
	</script>


</body>
</html>
