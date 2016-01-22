<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>客户管理</title>
</head>
<body>
<h1>客户列表</h1>
<%-- TODO --%>

<table>
	<tr>
		<th>客户名称</th>
		<th>联系人</th>
		<th>电话号码</th>
		<th>邮箱地址</th>
		<th>操作</th>
	</tr>
	
	<c:forEach var="customer" items="${costomerList}">
		<tr>
			<td>${customer.name}</td>
			<td>${customer.contact}</td>
			<td>${customer.telephone}</td>
			<td>${customer.email }</td>
			<td>
				<a>编辑</a>
				<a>删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>