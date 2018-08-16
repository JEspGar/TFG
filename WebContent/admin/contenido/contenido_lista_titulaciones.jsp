<%-- 
    Document   : contenido_registro_alumno
    Created on : 17-sep-2009, 10:40:51
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<h3>Seleccione una titulacion</h3>

<!-- Formulario de registro de clientes -->
<html:form action="admin/altaAsignatura">
        
      <label>Titulacion</label>   
       <html:select property="titulacion" value="Select">
		<html:optionsCollection name="asignaturasForm" property="titulacionesList"
		label="label" value="value" />
		</html:select>
		<html:errors property="asignatura.titulacion.vacio"></html:errors>
        <html:errors property="asignatura.titulacion.extenso"></html:errors>

      <br />
      <html:submit property="method" value="Seleccionar" styleClass="button" onclick="submitForm()" />

</html:form>
