package web.actions;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * @author javier.albert
 */
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.*;
import java.util.Vector;
import web.recursos.*;


public class opListados extends DispatchAction
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
       Vector asignaturas = gAsignaturas.listaAsignaturasTotal(request.getParameter("titulacion"));

       //Si hay asignaturas
       if (asignaturas != null && asignaturas.size() > 0)
       {
           //Se envia en la peticion el vector de asignaturas
           request.setAttribute("vectorAsignaturas", asignaturas);
           return mapping.findForward("asignaturasListado");
       }
       //Si no hay asignaturas o no se ha cumplido alguna condición anterior
       return mapping.findForward("error_listado_index");
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
       Vector grupos = gAsignaturas.listaGrupos(request.getParameter("asignatura"));

       String nombre_asignatura = gAsignaturas.nombreAsociado(request.getParameter("asignatura"));

       //Si hay grupos
       if (grupos != null && grupos.size() > 0)
       {
           //Se envia en la peticion el vector de grupos
           request.setAttribute("vectorGrupos", grupos);
           request.setAttribute("nombreAsignatura", nombre_asignatura);
           return mapping.findForward("listadoGrupos");
       }

       //Si no hay grupos o no se ha cumplido alguna condición anterior
       return mapping.findForward("error_grupos_index");
    }
    
    ///// listaAlumnosGrupo

    public ActionForward listaAlumnosGrupo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
        //Se obtiene el driver y la url de la BBDD del fichero web.xml
       String driver = this.getServlet().getServletContext().getInitParameter("driver");
       String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
       String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
       String password = this.getServlet().getServletContext().getInitParameter("password");

       gestionAlumnos gAlumnos= new gestionAlumnos(driver,url,usuario,password);
       Vector alumnosLista= gAlumnos.listaAlumnosGrupo(request.getParameter("codigoLab"));
       if (alumnosLista!=null && alumnosLista.size()>0){
           request.setAttribute("vectorAlumnosLista", alumnosLista);
           request.setAttribute("codigoLab", request.getParameter("codigoLab"));
           return mapping.findForward("listaGrupoIndividual");
       }
       //Recuperamos el vector de grupos
        return mapping.findForward("error_grupos_index");

    }
}