/**
 *
 * @author cc
 */

package web.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.Enumeration;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.*;
import org.apache.struts.util.LabelValueBean;

import web.recursos.*;
import web.forms.*;
import web.data.*;


public class mantenimientoTitulacion extends DispatchAction
{
    
    public ActionForward crear (ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
    {
       //Obtenemos los datos de la conexion del fichero web.xml
       String driver = this.getServlet().getServletContext().getInitParameter("driver");
       String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
       String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
       String password = this.getServlet().getServletContext().getInitParameter("password");
       
       String mensaje ="";
       boolean insertada=false;

       //Definimos el objeto formulario
       titulacionForm formulario = (titulacionForm)form;
       
       //Validamos los posibles errores del formulario
       ActionErrors errores = formulario.validate(mapping, request);
       
       gestionTitulaciones gTitulaciones = new gestionTitulaciones(driver,url,usuario,password);
       
       //Si hay errores
       if (!errores.isEmpty()){
    	           
           //Se guardan los errores en la request
           saveErrors(request, errores);

           return mapping.findForward("titulacion_form");

       }else {
    	   titulacion existe= gTitulaciones.comprobarTitulacion(formulario.getCodigo(), formulario.getTitulo());
    	   if (existe.getCodigo()==null) {
    		   insertada = gTitulaciones.registrarTitulacion(formulario);
    	   }else {
    		   insertada = false;
    	   }
       }
       
       if (insertada==true) {
    	   mensaje="titulacion_insertada";
       }else {
    	   mensaje="error_creacion_titulacion";
       }
       
       return mapping.findForward(mensaje);
    }
    
    public ActionForward mantener (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		titulacionForm formulario2 = (titulacionForm)form;
		asignaturasForm formulario = new asignaturasForm();
		
		//Recuperamos el vector de titulaciones
		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
		Vector titulaciones = gAsignaturas.listaTitulaciones();
		Enumeration contador= titulaciones.elements();
		ArrayList listaTitulaciones = new ArrayList();
		while (contador.hasMoreElements()) {
			titulacion titulacion=(titulacion)contador.nextElement();
			listaTitulaciones.add(new LabelValueBean((String)titulacion.getTitulacion(), (String)titulacion.getCodigo()));
		}
		
		formulario.setTitulacionesList(listaTitulaciones);
		formulario2.setTitulacionesList(listaTitulaciones);
		
		//Si hay titulaciones
		if (titulaciones != null && titulaciones.size() > 0)
		{
			//Se envia en la peticion el vector de titulaciones
			request.setAttribute("titulacionesList", listaTitulaciones);
			
			return mapping.findForward("lista_titulaciones");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("lista_titulaciones");		
	}
      
    public ActionForward buscarTitulacion (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		titulacionForm formulario = (titulacionForm)form;
		
		//Recuperamos los datos de la asignatura
		gestionTitulaciones gTitulaciones = new gestionTitulaciones(driver,url,usuario,password);
		titulacion titulacion = gTitulaciones.datosTitulacion(formulario.getCodigo());
		
		//Si hay titulaciones
		if (titulacion.getCodigo() != null)
		{
			//Se envia en la peticion el vector de titulaciones
			formulario.setCodigo(titulacion.getCodigo());
			formulario.setTitulo(titulacion.getTitulacion());
			request.setAttribute("titulacionForm", formulario);
			return mapping.findForward("mantenimiento_form");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("lista_titulaciones");		
	}
    
    public ActionForward modificarTitulacion (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		String pantalla ="";
		
		//Definimos el objeto formulario
		titulacionForm formulario = (titulacionForm)form;
		
		//Validamos los posibles errores del formulario
	    ActionErrors errores = formulario.validate(mapping, request);
		
		//Recuperamos el vector de titulaciones
		gestionTitulaciones gTitulaciones = new gestionTitulaciones(driver,url,usuario,password);
		
		boolean modificada=false;
		
		if (errores.isEmpty()) {
  		   modificada = gTitulaciones.modificarTitulacion(formulario);
  		   if (modificada){
  			   pantalla="titulacion_modificada";
  		   }
		}else {
			//Se guardan los errores en la request
	        saveErrors(request, errores);
	        request.setAttribute("titulaicionForm", formulario);
	        pantalla="mantenimiento_form";
		}
		return mapping.findForward(pantalla);
	}
    
    public ActionForward borrarTitulacion (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		titulacionForm formulario = (titulacionForm)form;
		
		//Recuperamos el vector de titulaciones
		gestionTitulaciones gTitulaciones = new gestionTitulaciones(driver,url,usuario,password);
		boolean borrada = gTitulaciones.borrarTitulacion(formulario.getCodigo());
		
		//Si se ha borrado con exito
		if (borrada){			
			return mapping.findForward("titulacion_borrada");		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("lista_titulaciones");		
	}
}
