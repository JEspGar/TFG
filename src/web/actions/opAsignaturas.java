/**
 *
 * @author cc
 */

package web.actions;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.*;

import java.util.Calendar;
import java.util.Date;

import sun.util.calendar.LocalGregorianCalendar.Date;

import java.util.Vector;

import web.data.asignatura;
import web.recursos.*;


public class opAsignaturas extends DispatchAction
{
    public ActionForward verAsignaturas (ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
    {
 
       //Se obtiene el driver y la url de la BBDD del fichero web.xml
       String driver = this.getServlet().getServletContext().getInitParameter("driver");
       String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
       String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
       String password = this.getServlet().getServletContext().getInitParameter("password");
       
      
       //Recuperamos el vector de asignaturas
       gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
       Vector asignaturas = gAsignaturas.listaAsignaturas(request.getParameter("titulacion"));

       //Si hay asignaturas
       if (asignaturas != null && asignaturas.size() > 0)
       {
           //Se envia en la peticion el vector de asignaturas
           request.setAttribute("vectorAsignaturas", asignaturas);
           
           return mapping.findForward("asignaturas_index");

       }

       //Si no hay asignaturas o no se ha cumplido alguna condición anterior
       return mapping.findForward("error_asignaturas_index");
    }

    public ActionForward verGrupos (ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
    {
       //Se obtiene el driver y la url de la BBDD del fichero web.xml
       String driver = this.getServlet().getServletContext().getInitParameter("driver");
       String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
       String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
       String password = this.getServlet().getServletContext().getInitParameter("password");

       //Recuperamos el vector de grupos
       gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
       
       //Comprobamos que la asignatura este activa, si no lo esta no se devuelven valores de grupos
       asignatura asignatura = gAsignaturas.datosAsignatura(request.getParameter("asignatura"));
       Date fechaActual = new Date();
       Vector grupos = null;
       if(asignatura.getFechaInicio().before(fechaActual) && asignatura.getFechaFin().after(fechaActual)) {
    	   grupos = gAsignaturas.listaGrupos(request.getParameter("asignatura"));
       }else {
    	   grupos = null;
       }
       
       String nombre_asignatura = gAsignaturas.nombreAsociado(request.getParameter("asignatura"));

       //Si hay grupos
       if (grupos != null && grupos.size() > 0)
       {
           //Se envia en la peticion el vector de grupos
           request.setAttribute("vectorGrupos", grupos);
           request.setAttribute("nombreAsignatura", nombre_asignatura);

           return mapping.findForward("grupos_index");
       }

       //Si no hay grupos o no se ha cumplido alguna condición anterior
       return mapping.findForward("error_grupos_index");
    }
}
