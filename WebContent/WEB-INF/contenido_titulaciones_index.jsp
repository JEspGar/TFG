<%-- 
    Document   : contenido_titulaciones_index
    Created on : 16-sep-2009, 10:33:21
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>


<h3 style="float: left;">
	<%-- <html:link forward="menu_administracion">Menu Administracion</html:link> --%>
	<html:link forward="menu_administracion"><img alt="administracion" src="images/administracion.jpg" width="25" height="25"></html:link>
</h3>
<br />
<br />

<h1>Inscripci&oacute;n Laboratorios</h1>

<br />

<center>
	<table>

		<tr>
			<th><center>
					<strong>TITULACI&Oacute;N</strong>
				</center></th>
		</tr>
		<logic:iterate id="titulacion" name="vectorTitulaciones"
			scope="request" type="web.data.titulacion">
			<tr>
				<td>
					<h3>
						<html:link action="/opAsignaturas?operacion=verAsignaturas"
							paramId="titulacion" paramName="titulacion"
							paramProperty="titulacion">
							<bean:write name="titulacion" property="titulacion"></bean:write>
						</html:link>
					</h3>
				</td>
			</tr>
		</logic:iterate>
	</table>

</center>
<br />
<h3>
	<html:link forward="dni_alumno">Consultar mis inscripciones</html:link>
</h3>


