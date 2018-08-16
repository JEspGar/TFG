<%-- 
    Document   : listado_alumnos
    Created on : 01-feb-2010, 13:46:17
    Author     : javier.albert
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <title>Listado de Grupos</title>
        <link rel="stylesheet" type="text/css" href="../inc/estilos.css">
    </head>

    <body>
        <!-- El contenedor de la cabecera empieza aqui -->
        <div id="header-wrap">
            <!-- El contenido de la cabecera empieza aqui -->
            <div id="header-content">

                <%@include file = "/WEB-INF/cabecera_index.jsp" %>

            </div>
        </div>

        <!-- El contenedor de contenido empieza aquí. -->
        <div id="content-wrap">
            <%@include file = "/profesor/contenido/contenido_mantenimiento_grupo_grupos.jsp" %>
            <h3><html:link forward="menu_profesor" >Volver</html:link></h3>
        </div>

    </body>
</html:html>