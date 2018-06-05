<%-- 
    Document   : contenido_grupos_index
    Created on : 16-sep-2009, 12:26:02
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
            <th><h3><bean:write scope="request" name="nombreAsignatura"></bean:write></h3></th>
        </tr>
</table></center>

<center><table>
   <tr>
       <th><center><strong>D&Iacute;A</strong></center></th>

       <th><center><strong>HORA</strong></center></th>

       <th><center><strong>OBSERVACIONES</strong></center></th>

       <th><center><strong>PLAZAS LIBRES</strong></center></th>
   </tr>

   <bean:define id="nombreAsignatura" name="nombreAsignatura"/>
   <%
        String nombre_asignatura = (String) pageContext.findAttribute("nombreAsignatura");
        session.setAttribute("nombre_asignatura", nombre_asignatura);
   %>


   <logic:iterate id="grupo" name="vectorGrupos" scope="request" type="web.data.grupo">
       <tr>
            <bean:define id="plazasTot" name="grupo" property="plazas"/>
            <bean:define id="plazasOcupadas" name="grupo" property="plazasOcupadas"/>
         
              <%
                Integer plazasTotal = (Integer) pageContext.findAttribute("plazasTot");
                Integer plazasOcu = (Integer) pageContext.findAttribute("plazasOcupadas");
              

                Integer plazasLibres = plazasTotal - plazasOcu;

                if (plazasLibres > 0)
                {
                   
              %>
              <td><center><html:link forward="registro_alumno" paramId="codGrupo" paramName="grupo" paramProperty="codigoLab"></center><bean:write name="grupo" property="dia"></bean:write></html:link></td>
                 <td align="center"><center><html:link forward="registro_alumno" paramId="codGrupo" paramName="grupo" paramProperty="codigoLab"></center><bean:write name="grupo" property="hora"></bean:write></html:link></td>
                 <td><bean:write name="grupo" property="observaciones"></bean:write></td>
                 <td align="center"><center><html:link forward="registro_alumno" paramId="codGrupo" paramName="grupo" paramProperty="codigoLab"></center> <%=plazasLibres  %> </html:link></td>
              <%
                }
                else
                {
              %>
                 <td><bean:write name="grupo" property="dia"></bean:write></td>
                 <td align="center"><bean:write name="grupo" property="hora"></bean:write></td>
                 <td><bean:write name="grupo" property="observaciones"></bean:write></td>
                 <td align="center" style="color:red">Grupo Completo</td>
              <%
                }
              %>
       </tr>
   </logic:iterate>
</table></center>

<br/>
<br/>
<h3><html:link forward="dni_alumno">Consultar mis inscripciones</html:link></h3>