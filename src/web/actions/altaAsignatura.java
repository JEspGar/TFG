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


public class altaAsignatura extends DispatchAction
{
    
    public ActionForward inicializar (ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
    {
       //Obtenemos los datos de la conexion del fichero web.xml
       String driver = this.getServlet().getServletContext().getInitParameter("driver");
       String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
       String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
       String password = this.getServlet().getServletContext().getInitParameter("password");

       //Definimos el objeto formulario
       asignaturasForm formulario = (asignaturasForm)form;
       
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
       
     //Si hay titulaciones
       if (titulaciones != null && titulaciones.size() > 0)
       {
           //Se envia en la peticion el vector de titulaciones
           request.setAttribute("titulacionesList", listaTitulaciones);

           return mapping.findForward("asignatura_form");

       }

       //Si no hay titulaciones o no se ha cumplido alguna condición anterior
       return mapping.findForward("error_titulaciones_index");
       
    }
    
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
       asignaturasForm formulario = (asignaturasForm)form;
       
       //Validamos los posibles errores del formulario
       ActionErrors errores = formulario.validate(mapping, request);
       
       gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
       
       //Si hay errores
       if (!errores.isEmpty()){
    	   //Recuperamos el vector de titulaciones
           Vector titulaciones = gAsignaturas.listaTitulaciones();
           Enumeration contador= titulaciones.elements();
           ArrayList listaTitulaciones = new ArrayList();
           while (contador.hasMoreElements()) {
        	   titulacion titulacion=(titulacion)contador.nextElement();
        	   listaTitulaciones.add(new LabelValueBean((String)titulacion.getTitulacion(), (String)titulacion.getCodigo()));
           }
           
           formulario.setTitulacionesList(listaTitulaciones);
           
           //Se envia en la peticion el vector de titulaciones
           request.setAttribute("titulacionesList", listaTitulaciones);
           
           //Se guardan los errores en la request
           saveErrors(request, errores);

           return mapping.findForward("asignatura_form");

       }else {
    	   asignatura existe= gAsignaturas.comprobarAsignatura(formulario.getCodigo(), formulario.getTitulo(), formulario.getTitulacion());
    	   if (existe.getCodigo()==null) {
    		   insertada = gAsignaturas.registrarAsignatura(formulario);
    	   }else {
    		   insertada = false;
    	   }
       }
       
       if (insertada==true) {
    	   mensaje="asignatura_insertada";
       }else {
    	   mensaje="error_creacion_asignatura";
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
		asignaturasForm formulario = (asignaturasForm)form;
		
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
		asignaturasForm formulario = (asignaturasForm)form;
		
		//Recuperamos el vector de titulaciones
		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
		Vector asignaturas = gAsignaturas.listaAsignaturasTitulacion(formulario.getTitulacion());
		Enumeration contador= asignaturas.elements();
		ArrayList listaAsignaturas = new ArrayList();
		while (contador.hasMoreElements()) {
			asignatura asignatura=(asignatura)contador.nextElement();
			listaAsignaturas.add(new LabelValueBean((String)asignatura.getTitulo(), (String)asignatura.getCodigo()));
		}
		
		formulario.setAsignaturasList(listaAsignaturas);
		
		//Si hay titulaciones
		if (asignaturas != null && asignaturas.size() > 0)
		{
			//Se envia en la peticion el vector de titulaciones
			request.setAttribute("asignaturasList", listaAsignaturas);
			
			return mapping.findForward("lista_asignaturas");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("error_titulaciones_index");		
	}
    
    public ActionForward buscarAsignatura (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		asignaturasForm formulario = (asignaturasForm)form;
		
		//Recuperamos los datos de la asignatura
		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
		asignatura asignatura = gAsignaturas.datosAsignatura(formulario.getCodigo());
		
		//Si hay titulaciones
		if (asignatura.getCodigo() != null)
		{
			//Se envia en la peticion el vector de titulaciones
			formulario.setCodigo(asignatura.getCodigo());
			formulario.setEmail(asignatura.getEmail());
			SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
			formulario.setFechaFin(formato1.format(asignatura.getFechaFin()));
			formulario.setFechaInicio(formato1.format(asignatura.getFechaInicio()));
			formulario.setResponsable(asignatura.getResponsable());
			formulario.setTelefono(asignatura.getTelefono());
			formulario.setTitulo(asignatura.getTitulo());
			formulario.setTitulacion(asignatura.getTitulacion());
			request.setAttribute("asignaturasForm", formulario);
			return mapping.findForward("mantenimiento_form");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("lista_titulaciones");		
	}
    
    public ActionForward modificarAsignatura (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		String pantalla ="";
		
		//Definimos el objeto formulario
		asignaturasForm formulario = (asignaturasForm)form;
		
		//Validamos los posibles errores del formulario
	    ActionErrors errores = formulario.validate(mapping, request);
		
		//Recuperamos el vector de titulaciones
		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
		
		boolean modificada=false;
		
		if (errores.isEmpty()) {
  		   modificada = gAsignaturas.modificarAsignatura(formulario);
  		   if (modificada){
  			   pantalla="asignatura_borrada";
  		   }
		}else {
			//Se guardan los errores en la request
	        saveErrors(request, errores);
	        request.setAttribute("asignaturasForm", formulario);
	        pantalla="mantenimiento_form";
		}
		return mapping.findForward(pantalla);
	}
    
    public ActionForward borrarAsignatura (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		asignaturasForm formulario = (asignaturasForm)form;
		
		//Recuperamos el vector de titulaciones
		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
		boolean borrada = gAsignaturas.borrarAsignatura(formulario.getCodigo());
		
		//Si se ha borrado con exito
		if (borrada){			
			return mapping.findForward("asignatura_borrada");		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("lista_titulaciones");		
	}
}
