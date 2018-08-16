<%-- 
    Document   : error_alumno_inscrito
    Created on : 21-sep-2009, 10:45:48
    Author     : cc
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
    <title>Inscripcion Laboratorios</title>
    <link rel="stylesheet" type="text/css" href="inc/estilos.css">
</head>

<body>

    <!-- El contenedor de la cabecera empieza aqui -->
    <div id="header-wrap">
        <!-- El contenido de la cabecera empieza aqui -->
        <div id="header-content">

            <%@include file = "WEB-INF/cabecera_index.jsp" %>

        </div>
    </div>

     <!-- El contenedor de contenido empieza aquí. -->
    <div id="content-wrap">

           <%@include file = "WEB-INF/contenido_alumno_inscrito_previamente.jsp" %>

    </div>

</body>

</html:html>