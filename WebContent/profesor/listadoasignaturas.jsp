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

            <%
                        if (request.getParameter("titulacion") != null) {
                            session.setAttribute("titulacion", request.getParameter("titulacion"));
                        }
            %>
            <h3>Titulacion:
                <%
                            String titulacion = (String) session.getAttribute("titulacion");
                            out.println(titulacion);
                %>
            </h3>

            <center><table>
                    <tr>
                        <th><center><strong>ASIGNATURA</strong></center></th>

                        <th><center><strong>PROFESOR</strong></center></th>

                        <th><center><strong>FOTOGRAF&Iacute;A</strong></center></th>
                    </tr>

                    <logic:iterate id="asignatura" name="vectorAsignaturas" scope="request" type="web.data.asignatura">
                        <tr>
                            <td>
                                <h4><html:link action="/opListados?operacion=verGrupos" paramId="asignatura" paramName="asignatura" paramProperty="codigo" >
                                        <bean:write name="asignatura" property="titulo"></bean:write>
                                    </html:link>
                                </h4>
                            </td>
                            <td align="center"><h4><bean:write name="asignatura" property="responsable"></bean:write></h4></td>
                            <td align="center"><img src="images/null.GIF" width="60" height="55" alt="imagen-profesor"  class="float-left" /></td>

                        </tr>

                    </logic:iterate>
                </table></center>
                <html:link action="profesor/listadoAsignaturas" >Volver</html:link>
        </div>

    </body>
</html:html>