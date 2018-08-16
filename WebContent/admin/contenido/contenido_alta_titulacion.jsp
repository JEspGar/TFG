<%-- 
    Document   : contenido_registro_alumno
    Created on : 17-sep-2009, 10:40:51
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<h3>Rellene el siguiente formulario para dar de alta la titulacion</h3>

<!-- Formulario de registro de clientes -->
<html:form action="admin/mantenimientoTitulacion">

      <label>Codigo</label>
      <html:text property="codigo" size="29"></html:text>
        <html:errors property="titulacion.codigo.vacio"></html:errors>
        <html:errors property="titulacion.codigo.extenso"></html:errors>

      <label>Nombre</label>
      <html:text property="titulo" size="29" ></html:text>
        <html:errors property="titulacion.titulo.vacio"></html:errors>
        <html:errors property="titulacion.titulo.extenso"></html:errors>

      <br />
      <html:submit property="method" value="Crear" styleClass="button" onclick="submitForm()" />
      <html:button property="method" styleClass="button" onclick="volver()">Volver</html:button>

</html:form>
