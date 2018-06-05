<%-- 
    Document   : contenido_dni_alumno
    Created on : 23-sep-2009, 12:39:41
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<h3>Indique su DNI para ver los grupos en los que se ha inscrito</h3>

<!-- Formulario de busqueda de grupos -->
<html:form action="/verInscripciones" method="post">

      <label>DNI</label>
      <html:text property="dni" value="" size="29"></html:text>
        <html:errors property="usuario.dni.vacio"></html:errors>
        <html:errors property="usuario.registro.extenso"></html:errors>

      <br />
      <html:submit value="Enviar" styleClass="button" ></html:submit>

</html:form>
