<%-- 
    Document   : listado_alumnos
    Created on : 01-feb-2010, 13:46:17
    Author     : javier.albert
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

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
            <center><h2>Listado de Alumnos por Asignatura / Grupo</h2></center>
            <h3>Titulacion:
            <%
                String titulacion=(String) session.getAttribute("titulacion");
                out.println(titulacion);
            %>
            <br/>
            Asignatura: <bean:write scope="request" name="nombreAsignatura"></bean:write>
            </h3>
            <%
                session.setAttribute("Asignatura", request.getAttribute("nombreAsignatura"));
            %>
            <center><table>
                    <tr>
                        <th>Grupo</th>
                        <th>Dia</th>
                        <th>Hora</th>
                        <th>Observaciones</th>
                    </tr>
                <logic:iterate id="grupo" name="vectorGrupos" scope="request" type="web.data.grupo">
                        <tr>                            
                            <td><html:link action="/opListados?operacion=listaAlumnosGrupo" paramId="codigoLab" paramName="grupo" paramProperty="codigoLab"><bean:write name="grupo" property="codigoLab"></bean:write></html:link></td>
                            <td><html:link action="/opListados?operacion=listaAlumnosGrupo" paramId="codigoLab" paramName="grupo" paramProperty="codigoLab"><bean:write name="grupo" property="dia"></bean:write></html:link></td>
                            <td><html:link action="/opListados?operacion=listaAlumnosGrupo" paramId="codigoLab" paramName="grupo" paramProperty="codigoLab"><bean:write name="grupo" property="hora"></bean:write></html:link></td>
                            <td><html:link action="/opListados?operacion=listaAlumnosGrupo" paramId="codigoLab" paramName="grupo" paramProperty="codigoLab"><bean:write name="grupo" property="observaciones"></bean:write></html:link></td>
                            
                        </tr>
                    </logic:iterate>
                </table></center>
            <html:link action="profesor/listadoAsignaturas" >Volver</html:link>
        </div>
    </body>
</html:html>