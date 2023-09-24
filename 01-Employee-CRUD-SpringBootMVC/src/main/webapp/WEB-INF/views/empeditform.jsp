<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="violet">
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div align="center">
		<h1>Edit Employee</h1>
		<form:form method="POST" action="/Employee-CRUD-MVC/editsave">
			<table bgcolor="cyan">
				<tr>
					<td></td>
					<td><form:hidden path="id" /></td>
				</tr>
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
					<td></td>
					<td><input type="submit" value="Update" /></td>
				</tr>

			</table>
		</form:form>
	</div>
</body>
</html>