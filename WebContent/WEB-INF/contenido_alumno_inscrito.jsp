<%-- 
    Document   : contenido_alumno_inscrito
    Created on : 21-sep-2009, 12:41:43
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<br/>
<h1>Inscripci&oacute;n Laboratorios</h1>

<br/>

<h3 style="color:blue"><bean:write name="registroForm" property="nombre"></bean:write> <bean:write name="registroForm" property="apellidos"></bean:write></h3>
<h3>se ha inscrito en </h3>
<h3 style="color:blue"><bean:write name="asignatura"></bean:write> de <bean:write name="carrera"></bean:write></h3>

<br/>
<h3>en el horario:</h3>
<h3 style="color:blue"><bean:write name="grupo" property="dia"></bean:write> a las <bean:write name="grupo" property="hora"></bean:write> </h3>
<br/>
<h3> Observaciones: </h3>
<h3 style="color:blue"><bean:write name="grupo" property="observaciones"></bean:write></h3>


<bean:define id="email" name="registroForm" property="email"></bean:define>
<bean:define id="nombre" name="registroForm" property="nombre"></bean:define>
<bean:define id="apellidos" name="registroForm" property="apellidos"></bean:define>
<bean:define id="asignatura" name="asignatura"></bean:define>
<bean:define id="carrera" name="carrera"></bean:define>
<bean:define id="dia" name="grupo" property="dia"></bean:define>
<bean:define id="hora" name="grupo" property="hora"></bean:define>


<%
    //Adjuntamos en la sesion todos los datos que necesitamos para el email de confirmacion

    String emailAux = (String) pageContext.findAttribute("email");
    String nombreAux = (String) pageContext.findAttribute("nombre");
    String apellidosAux = (String) pageContext.findAttribute("apellidos");
    String asignaturaAux = (String) pageContext.findAttribute("asignatura");
    String carreraAux = (String) pageContext.findAttribute("carrera");
    String diaAux = (String) pageContext.findAttribute("dia");
    String horaAux = (String) pageContext.findAttribute("hora");

    session.setAttribute("email", emailAux);
    session.setAttribute("nombre", nombreAux);
    session.setAttribute("apellidos", apellidosAux);
    session.setAttribute("asignatura", asignaturaAux);
    session.setAttribute("carrera", carreraAux);
    session.setAttribute("dia", diaAux);
    session.setAttribute("hora", horaAux);
%>

<br/>
<br/>
<br/>
<h1><html:link action="/correoConfirmacion">CONTINUAR</html:link></h1>