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
<html:form action="mantenimientoGrupo">
        
      <label>Titulacion</label>   
       <html:select property="codigoTit" value="Select">
		<html:optionsCollection name="grupoForm" property="titulacionesList"
		label="label" value="value" />
		</html:select>

      <br />
      <html:submit property="method" value="Seleccionar" styleClass="button" onclick="submitForm()" />

</html:form>
