<%-- 
    Document   : titulaciones_index
    Created on : 16-sep-2009, 10:32:45
    Author     : cc
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
    <title>Inscripcion Laboratorios</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/inc/estilos.css">
</head>

<body>

    <!-- El contenedor de la cabecera empieza aqui -->
    <div>
    <div id="header-wrap">
        <!-- El contenido de la cabecera empieza aqui -->
        <div id="header-content">

            <%@include file = "/WEB-INF/cabecera_index.jsp" %>
		
        </div>
    </div>    
    </div>
	
     <!-- El contenedor de contenido empieza aqu�. -->
    <div id="content-wrap">
			
           <%@include file = "/admin/contenido/contenido_menu_administracion.jsp" %>

    </div>

</body>

</html:html>
