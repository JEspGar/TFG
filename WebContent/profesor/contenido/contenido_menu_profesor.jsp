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
       <th><center><strong>MENU DE PROFESOR</strong></center></th>
   </tr>
   <tr>
       <td>
           <h3><html:link action="/profesor/mantenimientoGrupo.do?method=listarTitulaciones">Crear Grupo</html:link>
           </h3>
       </td>    
   </tr>
   <tr>
       <td>
           <h3><html:link action="/profesor/mantenimientoGrupo.do?method=listarTitulacionesMod">Mantener Grupo</html:link>
           </h3>
       </td>    
   </tr>
   <tr>
       <td>
           <h3><html:link action="/profesor/listadoAsignaturas.do">Consultar Listados</html:link>
           </h3>
       </td>    
   </tr>
</table>

</center>
<br/>
<h3><html:link action="/logoff.do">Salir</html:link></h3>


