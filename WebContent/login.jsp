<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean" %>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Inscripcion Laboratorios - Login</title>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/inc/estilos.css">		
	</head>
	<body>
		<div id="header-wrap">
	        <!-- El contenido de la cabecera empieza aqui -->
	        <div id="header-content">
	
	            <%@include file = "WEB-INF/cabecera_index.jsp" %>
			
	        </div>
    	</div>
    	<div id="content-wrap">
			<h1><bean:message key="web.title" /></h1>
			<p><bean:message key="web.login.message" /></p>
			<html:errors  />
			<html:form action="login">	
				<label>Usuario</label>
			    <html:text property="username" size="29"></html:text>
			
			    <label>Password</label>
			    <html:password property="password" size="29" ></html:password>
			       		
				<p><html:submit><bean:message key='web.login.button.submit' /></html:submit></p>
			</html:form>
		</div>
	</body>
</html:html>