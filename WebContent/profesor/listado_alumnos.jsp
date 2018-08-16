<%-- 
    Document   : listado_alumnos
    Created on : 01-feb-2010, 13:46:17
    Author     : javier.albert
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<title>Listado Alumnos Asignatura - Titulaciones!!</title>
<link rel="stylesheet" type="text/css" href="../inc/estilos.css">
</head>

<body>

	<!-- El contenedor de la cabecera empieza aqui -->
	<div id="header-wrap">
		<!-- El contenido de la cabecera empieza aqui -->
		<div id="header-content">

			<%@include file="/WEB-INF/cabecera_index.jsp"%>

		</div>
	</div>

	<!-- El contenedor de contenido empieza aquí. -->
	<div id="content-wrap">
		<br />
		<br />
		<br />
		<center>
			<h2>Listado de Alumnos por asignatura</h2>
		</center>
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
								<html:link action="/profesor/opListados?operacion=verAsignaturas"
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
		<h3>
			<html:link forward="menu_administracion">Salir</html:link>
		</h3>
	</div>

</body>
</html:html>