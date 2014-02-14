<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<div class="panel-heading"><fmt:message key="tasks.tasks"/></div>
			<div class="panel-body">
				<a href="cad_item?task.id=${task.id}" class="btn btn-default btn-xs pull-right">
				  <span class="glyphicon glyphicon-plus"></span><fmt:message key="task.add.item"/>
				</a> 
			    <h3><input type="checkbox" id="id_completed_list"> ${task.title}</h3>
			</div>
			<!-- Table -->
			<table class="table">
				<c:forEach var="i" items="${task.items}">
				    <tr>
				    	<td><input type="checkbox" name="completed" value="${i.completed}"></td>			    	
				    	<td>${i.description}</td>
				    	<td><a title='<fmt:message key="task.alter"/>' href="cad_item?task.id=${task.id}&id=${i.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
				    	<td><a title='<fmt:message key="task.delete"/>' href="item_delete/?id=${i.id}" onclick="show_modal(this.href); return false;"><span class="glyphicon glyphicon-remove"></span></a></td>
				    </tr>
				</c:forEach>	
				<c:if test="${fn:length(task.items) <= 0}">
					<h3 class="text-danger align-center"><fmt:message key="task.there.are.no.items.in.this.list.of.tasks" /> </h3>
				</c:if>		    
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
