/**
 *
 * @author cc
 */

package web.actions;

import javax.servlet.http.*;
import org.apache.struts.action.*;

import web.recursos.*;
import web.forms.*;
import web.data.*;


public class registroAlumno extends Action
{
    @Override
    public ActionForward execute (ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
    {
       //Obtenemos los datos de la conexion del fichero web.xml
       String driver = this.getServlet().getServletContext().getInitParameter("driver");
       String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
       String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
       String password = this.getServlet().getServletContext().getInitParameter("password");

       //Definimos el objeto formulario
       registroForm formulario = (registroForm)form;
       grupo objGrupo = new grupo();

       //Extraemos el nombre de la asignatura del ambito de sesión
       HttpSession sesion = request.getSession(false);

       String asignatura = (String)sesion.getAttribute("nombre_asignatura");
       String grupo = (String)sesion.getAttribute("codGrupo");
       String carrera = (String)sesion.getAttribute("titulacion");

       sesion.removeAttribute("nombre_asignatura");
       sesion.removeAttribute("codGrupo");
       sesion.removeAttribute("titulacion");
       
       gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
       String codTitulacion = gAsignaturas.codigoTitAsociado(carrera);
       String codAsignatura = gAsignaturas.codigoAsociado(asignatura,codTitulacion);

       //Comprobamos que el alumno no este inscrito en la asignatura
       gestionAlumnos gAlumnos = new gestionAlumnos(driver,url,usuario,password);
       boolean alumno_inscrito = gAlumnos.alumnoInscrito(formulario.dni,codAsignatura);

       if (alumno_inscrito)
       {
           return mapping.findForward("error_alumno_inscrito");
       }
       {
           if ((asignatura != null) && (grupo != null) && (carrera != null) && (!(gAsignaturas.grupoCompleto(grupo))))
           {
               //Se inserta el nuevo alumno en el grupo correspondiente en la base de datos
               if (gAlumnos.registrarAlumno(formulario,grupo,codAsignatura,carrera))
               {
                   //Se actualizan las plazas ocupadas del grupo
                   gAsignaturas.actualizarPlazas(grupo);

                   request.setAttribute("registroForm", formulario);
                   request.setAttribute("asignatura", asignatura);
                   request.setAttribute("carrera", carrera);

                   gAsignaturas.datosGrupo(objGrupo,grupo);
                   request.setAttribute("grupo", objGrupo);
               }
               else
               {
                   return mapping.findForward("error_alumno_inscrito");
               }

               //Devolvemos el forward correspondiente
               return mapping.findForward("alumno_inscrito");
           }
           else
           {
               return mapping.findForward("error_alumno_inscrito");
           }
          
       }
       
    }
}
