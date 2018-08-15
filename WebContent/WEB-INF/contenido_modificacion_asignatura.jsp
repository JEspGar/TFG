<%-- 
    Document   : contenido_registro_alumno
    Created on : 17-sep-2009, 10:40:51
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<h3>Edite los campos a modificar o pulse eliminar para borrar la asignatura</h3>

<!-- Formulario de registro de clientes -->
<html:form action="altaAsignatura">

      <label>Codigo</label>
      <html:text property="codigo" size="29" readonly="true"></html:text>

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

      <br />
      <html:submit property="method" value="Modificar" styleClass="button" onclick="submitForm()" />
      <html:submit property="method" value="Desactivar" styleClass="button" onclick="submitFormBorrado()" />
      <html:submit property="method" value="Reactivar" styleClass="button" onclick="submitFormActivacion()" />
      <html:submit property="method" value="Inicializar" styleClass="button" onclick="submitFormInicializar()" />
      <html:button property="method" styleClass="button" onclick="volver()">Volver</html:button>

</html:form>
