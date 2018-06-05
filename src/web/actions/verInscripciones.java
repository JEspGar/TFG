/**
 *
 * @author cc
 */

package web.actions;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.Vector;

import web.forms.*;
import web.recursos.*;
import web.data.*;

public class verInscripciones extends Action
{
    @Override
    public ActionForward execute (ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
    {
       inscrito alumno = new inscrito();
       grupo grupo = new grupo();

       //Obtenemos los datos de la conexion del fichero web.xml
       String driver = this.getServlet().getServletContext().getInitParameter("driver");
       String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
       String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
       String password = this.getServlet().getServletContext().getInitParameter("password");

       //Definimos el objeto formulario
       dniForm formulario = (dniForm)form;

       gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);

       //Comprobamos los grupos a los que está inscrito el alumno
       gestionAlumnos gAlumnos = new gestionAlumnos(driver,url,usuario,password);
       Vector grupos = gAlumnos.listaGrupos(formulario.dni);

       //Si hay inscripciones
       if (grupos != null && grupos.size() > 0)
       {
           alumno = (inscrito)grupos.firstElement();

           request.setAttribute("nombre",alumno.getNombre());
           request.setAttribute("apellidos",alumno.getApellidos());

           //Se envia en la peticion el vector de inscripciones
           request.setAttribute("vectorInscripciones", grupos);

           return mapping.findForward("inscripciones_index");

       }

       //Si no hay titulaciones o no se ha cumplido alguna condición anterior
       return mapping.findForward("error_inscripciones_index");

    }
}
