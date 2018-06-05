/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.actions;

/**
 *
 * @author javier.albert
 */
import javax.servlet.http.*;
import org.apache.struts.action.*;
import web.recursos.*;
import web.forms.*;
import web.data.*;
import java.util.Vector;

public class listadoAsignatura extends Action {
@Override
    public ActionForward execute (ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
    {
       //Se obtiene el driver y la url de la BBDD del fichero web.xml
       String driver = this.getServlet().getServletContext().getInitParameter("driver");
       String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
       String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
       String password = this.getServlet().getServletContext().getInitParameter("password");


       //Recuperamos el vector de titulaciones
       gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
       Vector titulaciones = gAsignaturas.listaTitulaciones();

       //Si hay titulaciones
       if (titulaciones != null && titulaciones.size() > 0)
       {
           //Se envia en la peticion el vector de titulaciones
           request.setAttribute("vectorTitulaciones", titulaciones);

           return mapping.findForward("listado_index");

       }

       //Si no hay titulaciones o no se ha cumplido alguna condición anterior
       return mapping.findForward("error_listado_index");
    }

}
