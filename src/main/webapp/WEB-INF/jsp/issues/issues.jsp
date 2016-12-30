<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery.min.js"></script>
<title>main</title>
</head>
<body>
	<div>
		<div></div>
	</div>

	<script type="text/javascript">
		$.ajax({
			type : "get",
			url : "knowledgeSharing/issues/getList",
		}).done(function(msg) {
			alert("Data Saved: " + msg);
		});
	</script>
</body>
</html>