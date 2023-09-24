<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="lightblue"><%@ taglib
		uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1 align="center">Employees List</h1>
	<%-- 	<c:choose>
		<c:when test="${!empty empsList }"> --%>
	<table bgcolor="yellow" align="center" border="2" width="70%"
		cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Salary</th>
			<th>Designation</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="emp" items="${empsList}">
			<tr>
				<td>${emp.id}</td>
				<td>${emp.name}</td>
				<td>${emp.salary}</td>
				<td>${emp.designation}</td>
				<td><a href="editemp/${emp.id}">Edit</a></td>
				<td><a href="deleteemp/${emp.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<%-- 		</c:when>
		<c:otherwise>
			<h1 style="color: red; text-align: center">Record Not Found</h1>
		</c:otherwise>
	</c:choose>
	<h1 style="color: green; text-align: center">${resultMsg}</h1> --%>
	<br>
	<br />
	<div align="center" style="border:"2" ">
		<a href="empform">Add New Employee</a>
		<h3 style="text-align: center">
			<a href="./">HOME</a>
			</h3>
	</div>
</body>
</html>