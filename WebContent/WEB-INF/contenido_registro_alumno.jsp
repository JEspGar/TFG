<%-- 
    Document   : contenido_registro_alumno
    Created on : 17-sep-2009, 10:40:51
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<h3>Rellene el siguiente formulario para darse de alta en el grupo de laboratorio</h3>

<%
    if (request.getParameter("codGrupo") != null)
    {
        session.setAttribute("codGrupo", request.getParameter("codGrupo"));
    }
 %>

<!-- Formulario de registro de clientes -->
<html:form action="/registroAlumno.do?method=registrar"  method="post">

      <label>DNI</label>
      <html:text property="dni" value="" size="29"></html:text>
        <html:errors property="usuario.dni.vacio"></html:errors>
        <html:errors property="usuario.registro.extenso"></html:errors>

      <label>Nombre</label>
      <html:text property="nombre" value="" size="29" ></html:text>
        <html:errors property="nombre.registro.vacio"></html:errors>
        <html:errors property="nombre.registro.extenso"></html:errors>
        

      <label>Apellidos</label>
      <html:text property="apellidos" value="" size="29" ></html:text>
        <html:errors property="apellidos.registro.vacio"></html:errors>
        <html:errors property="apellidos.registro.extenso"></html:errors>

      <label>E-mail</label>
      <html:text property="email" value="" size="29" ></html:text>
        <html:errors property="email.registro.vacio"></html:errors>
        <html:errors property="email.registro.extenso"></html:errors>

      <label>Tel&eacute;fono</label>
      <html:text property="telefono" value="" size="29" ></html:text>
        <html:errors property="telefono.registro.extenso"></html:errors>

      <br />
      <html:submit value="Enviar" styleClass="button" ></html:submit>

</html:form>
