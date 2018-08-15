<!-- 
    Document   : contenido_inscripciones_index
    Created on : 23-sep-2009, 12:35:59
    Author     : cc
-->

<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<br/>
<h1>Inscripci&oacute;n Laboratorios</h1>

<br/>

<center><table>
        <tr>
            <th><h3><bean:write name="nombre" scope="request"></bean:write> <bean:write name="apellidos" scope="request"></bean:write></h3></th>
        </tr>
</table></center>

<center><table>
   <tr>
       <th><center><strong>ASIGNATURA</strong></center></th>
       <th><center><strong>CARRERA</strong></center></th>
       <th><center><strong>DIA</strong></center></th>
       <th><center><strong>HORA</strong></center></th>
       <th><center><strong>FECHA INSCRIPCI&Oacute;N</strong></center></th>
   </tr>


   <logic:iterate id="inscrito" name="vectorInscripciones" scope="request" type="web.data.inscrito">
       <tr>
           
             
             <td><bean:write name="inscrito" property="asignatura"></bean:write></td>
             <td><bean:write name="inscrito" property="carrera"></bean:write></td>
             <td><bean:write name="inscrito" property="dia"></bean:write></td>
             <td><bean:write name="inscrito" property="hora"></bean:write></td>
             <td><bean:write name="inscrito" property="inscrito"></bean:write></td>
             
       </tr>
   </logic:iterate>
</table></center>

<br/>
<br>
<h3> Si quieres cambiar de grupo alguna de tus inscripciones ve a la pantalla de inscripcion y selecciona el nuevo grupo haciendo la inscripcion con normalidad,
 a continuacion sigue las instrucciones.</h3>
<br/>
<br/>
<h1><html:link forward="welcome">CONTINUAR</html:link></h1>
