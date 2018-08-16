<%-- 
    Document   : contenido_registro_alumno
    Created on : 17-sep-2009, 10:40:51
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<h3>Rellene el siguiente formulario para dar de alta el grupo</h3>

<!-- Formulario de registro de clientes -->
<html:form action="profesor/mantenimientoGrupo">

      <label>Dia</label>
      <html:text property="dia" size="29" ></html:text>
        <html:errors property="grupo.dia.vacio"></html:errors>
        <html:errors property="grupo.dia.extenso"></html:errors>
        

      <label>Hora</label>
      <html:text property="hora" size="29" ></html:text>
        
      <label>Plazas</label>
      <html:text property="plazas" size="29" ></html:text>
        <html:errors property="grupo.plazas.vacio"></html:errors>
        <html:errors property="grupo.plazas.erroneo"></html:errors>
        
      <label>Observaciones</label>
      <html:text property="observaciones" size="29" ></html:text>

      <label>Codigo Asignatura</label>
      <html:text property="asigAsoc" size="29" readonly="true"></html:text>        

      <br />
      <html:submit property="method" value="Crear" styleClass="button" onclick="submitForm()" />
      <html:button property="method" styleClass="button" onclick="volver()">Volver</html:button>

</html:form>
