<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>       
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/resources/css/style.css"%></style>
</head>


<header>
<h1 align="center">myWork</h1>
</header>
<body>
<hr />
<div align="center">
<p style="color:red">
<c:if test="${not empty errormsg}">
   Error: ${errormsg}
</c:if>
</p>
<form:form action="loginEmployee" method="post" modelAttribute="employee">

Employee Passcode:<form:input class="input-f" type="password" maxlength="6" path="passcode" />

<input type="checkbox" onclick="showPass()">Show Passcode

<input class="button" type="submit" value="login" />

</form:form>

</div>
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