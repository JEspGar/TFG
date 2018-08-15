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
           <center>
           		<h3>Mantenimiento de grupos</h3>
	            <div id="tabla_grupos" style="width:80%;">
		            <datatables:table id="myTableId" data="${grupos}" row="grupo" dom="lEfrtiEp">
					  <datatables:column title="ID" property="codigoLab" sortable="false" /> 
					  <datatables:column title="ASIGNATURA" property="asigAsoc" />
					  <datatables:column title="DIA" property="dia" />
					  <datatables:column title="HORA" property="hora" /> 
					  <datatables:column title="PLAZAS" property="plazas" />
					  <datatables:column title="PLAZAS OCUPADAS" property="plazasOcupadas" />
					  <datatables:column title="OBSERVACIONES" property="observaciones" />
					  <datatables:column title="ACTIVO" property="activo" cssCellStyle="text-transform: uppercase;"/>
					  <datatables:column><a href="mantenimientoGrupo.do?method=buscarGrupo&grupo=${grupo.codigoLab}" >Seleccionar</a></datatables:column>
					  
					</datatables:table>
	            </div>
           </center>
            <p><br/><br/></p>
            <h3><html:link forward="menu_administracion" >Volver</html:link></h3>
        </div>

    </body>
</html:html>