<%-- 
    Document   : contenido_error_alumno_inscrito
    Created on : 21-sep-2009, 10:46:44
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<center>
	<h3>Mantenimiento de grupos</h3>
	<div id="tabla_grupos" style="width: 80%;">
		<datatables:table id="myTableId" data="${grupos}" row="grupo"
			dom="lEfrtiEp">
			<datatables:column title="ID" property="codigoLab" sortable="false" />
			<datatables:column title="ASIGNATURA" property="asigAsoc" />
			<datatables:column title="DIA" property="dia" />
			<datatables:column title="HORA" property="hora" />
			<datatables:column title="PLAZAS" property="plazas" />
			<datatables:column title="PLAZAS OCUPADAS" property="plazasOcupadas" />
			<datatables:column title="OBSERVACIONES" property="observaciones" />
			<datatables:column title="ACTIVO" property="activo"
				cssCellStyle="text-transform: uppercase;" />
			<datatables:column>
				<a
					href="mantenimientoGrupo.do?method=buscarGrupo&grupo=${grupo.codigoLab}">Seleccionar</a>
			</datatables:column>

		</datatables:table>
	</div>
</center>
<p>
	<br />
	<br />
</p>