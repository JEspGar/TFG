<%-- 
    Document   : contenido_titulaciones_index
    Created on : 16-sep-2009, 10:33:21
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<br/>
<h1>Inscripci&oacute;n Laboratorios</h1>

<br/>

<center><table>
   <tr>
       <th><center><strong>MENU DE ADMINISTRACI&Oacute;N</strong></center></th>
   </tr>
   <tr>
       <td>
           <h3><html:link forward="titulacion_form">Crear Titulacion</html:link>
           </h3>
       </td>    
   </tr>
   <tr>
       <td>
           <h3><html:link action="/mantenimientoTitulacion.do?method=mantener">Mantener Titulacion</html:link>
           </h3>
       </td>    
   </tr>
   <tr>
       <td>
           <h3><html:link action="/altaAsignatura.do?method=inicializar">Crear Asignatura</html:link>
           </h3>
       </td>    
   </tr>
   <tr>
       <td>
           <h3><html:link action="/altaAsignatura.do?method=mantener">Mantener Asignatura</html:link>
           </h3>
       </td>    
   </tr>
   <tr>
       <td>
           <h3><html:link action="/mantenimientoGrupo.do?method=listarTitulaciones">Crear Grupo</html:link>
           </h3>
       </td>    
   </tr>
   <tr>
       <td>
           <h3><html:link action="/mantenimientoGrupo.do?method=listarTitulacionesMod">Mantener Grupo</html:link>
           </h3>
       </td>    
   </tr>
   <tr>
       <td>
           <h3><html:link action="/listadoAsignaturas.do">Consultar Listados</html:link>
           </h3>
       </td>    
   </tr>
</table>

</center>
<br/>
<h3><html:link action="/logoff.do">Salir</html:link></h3>


