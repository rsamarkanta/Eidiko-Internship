<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="teal">
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1 align="center">Add New Employee</h1>
	<form:form method="post" modelAttribute="emp" action="empform">
		<table bgcolor="cyan" border="2" cellpadding="2" align="center">
			<tr>
				<td>Name :</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Salary :</td>
				<td><form:input path="salary" /></td>
			</tr>
			<tr>
				<td>Designation :</td>
				<td><form:input path="designation" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form:form>
	<h3 style="text-align: center">
		<a href="./">HOME</a>
	</h3>
</body>
</html>