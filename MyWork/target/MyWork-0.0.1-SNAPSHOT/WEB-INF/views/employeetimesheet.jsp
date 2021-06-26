<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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

<h3>Timesheet</h3>

<table border="1">
<tr>
<th>Day</th>
<th>Clock In</th>
<th>Clock Out</th>
<th>Hours worked</th>
</tr>
<c:forEach items="${list}" var="time">
	<tr>
		<td>${time.weekDay}</td>
		<td><fmt:formatDate value="${time.checkIn}" pattern="MM-dd-yyyy hh:mm:ss aa" /></td>
		<td><fmt:formatDate value="${time.checkOut}" pattern="MM-dd-yyyy hh:mm:ss aa" /></td>
		<td>${time.workedHours}</td>
		
	</tr>
	</c:forEach>

</table>


</body>
<footer><p align="right">
<script>

function showPass() {
  var x = document.getElementById("passcode");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}

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