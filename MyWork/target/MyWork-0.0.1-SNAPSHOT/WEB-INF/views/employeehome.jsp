<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/resources/css/style.css"%></style>
</head>


<header>
<a href="logout" style="float: right;color:blue;">Logout</a>
<h1 align="center">myWork</h1>
</header>

<body>
<hr />
<p style="color:red">
<c:if test="${not empty errormsg}">
   Error: ${errormsg}
</c:if>
</p>
<form action="clockin" name="clockin">
	<input class="button" type="submit" value="Clock In" /> 
</form>
<br>
<form action="clockout" name="clockout">
	<input class="button" type="submit" value="Clock Out" />
</form>
<br>
<form action="timesheet" name="timesheet">
	<input class="button" type="submit" value="View Timesheet" />
</form>

</body>
<footer><p align="right">
<script>
function dateToMMDDYY(date) {
    var d = date.getDate();
    var m = date.getMonth() + 1; //Month from 0 to 11
    var y = date.getFullYear();
    return '' +(m<=9 ? '0' + m : m)+ '/' + (d <= 9 ? '0' + d : d) + '/' + y  ;
}
document.write(dateToMMDDYY(new Date()));
</script>


</p></footer>

</html>