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

                <%@include file = "WEB-INF/cabecera_index.jsp" %>

            </div>
        </div>

        <!-- El contenedor de contenido empieza aquí. -->
        <div id="content-wrap">
            <br/><br/><br/>
            <center><h2>Listado de Alumnos por Asignatura / Grupo </h2>
                <h3>Titulación:
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

            <center><table>
                    <tr>
                        <th>Numero</th>
                        <th><center><strong>DNI</strong></center></th>

                        <th><center><strong>APELLIDOS</strong></center></th>

                        <th><center><strong>NOMBRE</strong></center></th>

                        <th><center><strong>EMAIL</strong></center></th>
                    </tr>
                    <%
                                int orden = 1;

                    %>
                    <logic:iterate id="inscrito" name="vectorAlumnosLista" scope="request" type="web.data.inscrito">
                        <tr>
                            <td><%out.print(orden);%></td>
                            <td>
                                <h4><bean:write name="inscrito" property="dni"></bean:write></h4>
                            </td>
                            <td>
                                <h4><bean:write name="inscrito" property="apellidos"></bean:write></h4>
                            </td>
                            <td>
                                <h4><bean:write name="inscrito" property="nombre" ></bean:write></h4>
                            </td>
                            <td><h4><bean:write name="inscrito" property="email"></bean:write></h4></td>
                            <% orden++;%>
                        </tr>

                    </logic:iterate>
                </table></center>
            <p><br/><br/><br/><br/></p>
            <html:link action="listadoAsignaturas" >Volver</html:link>

        </div>

    </body>
</html:html>