<%-- 
    Document   : contenido_registro_alumno
    Created on : 17-sep-2009, 10:40:51
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<h3>Rellene el siguiente formulario para dar de alta la asignatura</h3>

<!-- Formulario de registro de clientes -->
<html:form action="admin/altaAsignatura">

      <label>Codigo</label>
      <html:text property="codigo" size="29"></html:text>
        <html:errors property="asignatura.codigo.vacio"></html:errors>
        <html:errors property="asignatura.codigo.extenso"></html:errors>

      <label>Nombre</label>
      <html:text property="titulo" size="29" ></html:text>
        <html:errors property="asignatura.titulo.vacio"></html:errors>
        <html:errors property="asignatura.titulo.extenso"></html:errors>
        

      <label>Fecha de Inicio (dd/mm/yyyy)</label>
      <html:text property="fechaInicio" size="29" ></html:text>
        <html:errors property="asignatura.fechaInicio.vacio"></html:errors>
        <html:errors property="asignatura.fechaInicio.formato"></html:errors>
        
      <label>Fecha de Fin (dd/mm/yyyy)</label>
      <html:text property="fechaFin" size="29" ></html:text>
        <html:errors property="asignatura.fechaFin.vacio"></html:errors>
        <html:errors property="asignatura.fechaFin.formato"></html:errors>
        
      <label>Responsable</label>
      <html:text property="responsable" size="29" ></html:text>
        <html:errors property="asignatura.responsable.extenso"></html:errors>

      <label>E-mail</label>
      <html:text property="email" size="29" ></html:text>
        <html:errors property="asignatura.email.extenso"></html:errors>

      <label>Tel&eacute;fono</label>
      <html:text property="telefono" size="29" ></html:text>
        <html:errors property="asignatura.telefono.extenso"></html:errors>
        
      <label>Titulacion</label>   
       <html:select property="titulacion" value="Select">
		<html:optionsCollection name="asignaturasForm" property="titulacionesList"
		label="label" value="value" />
		</html:select>
		<html:errors property="asignatura.titulacion.vacio"></html:errors>
        <html:errors property="asignatura.titulacion.extenso"></html:errors>

      <br />
      <html:submit property="method" value="Crear" styleClass="button" onclick="submitForm()" />
      <html:button property="method" styleClass="button" onclick="volver()">Volver</html:button>

</html:form>
