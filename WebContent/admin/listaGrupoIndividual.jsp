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
        <title>Listado de Alumnos por Asignatura / Grupo</title>
        <link rel="stylesheet" type="text/css" href="inc/estilos.css">
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
            <br/><br/><br/>
            <center><h2>Listado de Alumnos por Asignatura / Grupo </h2>
                <h3>Titulacion:
                    <%
                                String titulacion = (String) session.getAttribute("titulacion");
                                out.println(titulacion);
                    %>
                </h3>
                <h3>Asignatura:
                    <%
                                String asignatura = (String) session.getAttribute("Asignatura");
                                out.println(asignatura);
                    %>
                </h3>
            </center>
            <h4>Fecha impresion:
                <%
                            java.util.Date fechactual = new java.util.Date();
                            java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            out.print(formato.format(fechactual));

                %>
            </h4>

            <center>
	            <div id="tabla_alumnos" style="width:80%;">
		            <datatables:table id="myTableId" data="${vectorAlumnosLista}" export="pdf,xls" dom="lEfrtiEp">
					  <datatables:column title="DNI" property="dni" sortable="false" /> 
					  <datatables:column title="NOMBRE" property="nombre" />
					  <datatables:column title="APELLIDOS" property="apellidos" />
					  <datatables:column title="EMAIL" property="email" cssCellStyle="text-transform: none;"/> 
					</datatables:table>
	            </div>
           </center>
            <p><br/><br/></p>
            <h3><html:link action="listadoAsignaturas" >Volver</html:link></h3>
        </div>

    </body>
</html:html>