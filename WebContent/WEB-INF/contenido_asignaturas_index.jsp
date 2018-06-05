<%-- 
    Document   : asignaturas_index
    Created on : 15-sep-2009, 12:02:11
    Author     : cc
--%>

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<br/>
<h1>Inscripci&oacute;n Laboratorios</h1>

<br/>

<%
    if (request.getParameter("titulacion") != null)
    {
        session.setAttribute("titulacion", request.getParameter("titulacion"));
    }
 %>

<center><table>
   <tr>
       <th><center><strong>ASIGNATURA</strong></center></th>
   
       <th><center><strong>PROFESOR</strong></center></th>

       <th><center><strong>FOTOGRAF&Iacute;A</strong></center></th>
   </tr>

   <logic:iterate id="asignatura" name="vectorAsignaturas" scope="request" type="web.data.asignatura">
   <tr>
       <td>
           <h4><html:link action="/opAsignaturas?operacion=verGrupos" paramId="asignatura" paramName="asignatura" paramProperty="codigo" >
                    <bean:write name="asignatura" property="titulo"></bean:write>
               </html:link>
           </h4>
       </td>
       <td align="center"><h4><bean:write name="asignatura" property="responsable"></bean:write></h4></td>
       <td align="center"><img src="images/null.GIF" width="60" height="55" alt="imagen-profesor"  class="float-left" /></td>
      
   </tr>
  
   </logic:iterate>
</table></center>

<br/>
<h3><html:link forward="dni_alumno">Consultar mis inscripciones</html:link></h3>