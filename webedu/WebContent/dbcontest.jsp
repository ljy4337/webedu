<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.Connection" %>   

<%@ page import = "javax.naming.Context" %>
<%@ page import = "javax.naming.InitialContext" %>
<%@ page import = "javax.sql.DataSource" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
Context ctx = null;
DataSource ds = null;
Connection dbcon = null;

%>
<%
		try{
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			dbcon = ds.getConnection();
			System.out.println("접속성공!!");
		}catch(Exception e){
			e.printStackTrace();
		}
%>		
</body>
</html>