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


public class mantenimientoGrupo extends DispatchAction
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
       grupoForm formulario = (grupoForm)form;
       
       //Validamos los posibles errores del formulario
       ActionErrors errores = formulario.validate(mapping, request);
       
       gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
       
       //Si hay errores
       if (!errores.isEmpty()){
    	           
           //Se guardan los errores en la request
           saveErrors(request, errores);

           return mapping.findForward("datos_grupo");

       }else {
    	   insertada = gGrupos.registrarGrupo(formulario);
       }
       
       if (insertada==true) {
    	   mensaje="grupo_insertado";
       }else {
    	   mensaje="error_creacion_grupo";
       }
       
       return mapping.findForward(mensaje);
    }
    
    public ActionForward listarTitulaciones (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		grupoForm formulario = new grupoForm();
		
		//Recuperamos el vector de titulaciones
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		Vector titulaciones = gGrupos.listaTitulaciones();
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
			request.setAttribute("grupoForm", formulario);
			
			return mapping.findForward("lista_titulaciones");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("lista_titulaciones");		
	}
    
    public ActionForward listarAsignaturas (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		grupoForm formulario = (grupoForm)form;
		
		//Recuperamos el vector de titulaciones
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		Vector asignaturas = gGrupos.listaAsignaturasTitulacion(formulario.getCodigoTit());
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
			request.setAttribute("grupoForm", formulario);
			
			return mapping.findForward("lista_asignaturas");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("error_titulaciones_index");		
	}
    
    public ActionForward inicializar (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		grupoForm formulario = (grupoForm)form;
		
		request.setAttribute("grupoForm", formulario);
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("datos_grupo");		
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
    
    public ActionForward listarTitulacionesMod (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		grupoForm formulario = new grupoForm();
		
		//Recuperamos el vector de titulaciones
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		Vector titulaciones = gGrupos.listaTitulaciones();
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
			request.setAttribute("grupoForm", formulario);
			
			return mapping.findForward("lista_titulaciones_modif");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("lista_titulaciones_modif");		
	}
    
    public ActionForward listarAsignaturasMod (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		grupoForm formulario = (grupoForm)form;
		
		//Recuperamos el vector de titulaciones
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		Vector asignaturas = gGrupos.listaAsignaturasTitulacion(formulario.getCodigoTit());
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
			request.setAttribute("grupoForm", formulario);
			
			return mapping.findForward("lista_asignaturas_modif");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("error_titulaciones_index");		
	}
    
    public ActionForward listarGruposMod (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		grupoForm formulario = (grupoForm)form;
		
		//Recuperamos el vector de titulaciones
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		Vector grupos = gGrupos.listaGruposAsignatura(formulario.getAsigAsoc());
		
		//Si hay titulaciones
		if (grupos != null && grupos.size() > 0)
		{
			//Se envia en la peticion el vector de titulaciones
			request.setAttribute("grupos", grupos);
			request.setAttribute("grupoForm", formulario);
			
			return mapping.findForward("lista_grupos_modif");
		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("error_titulaciones_index");		
	}
    
    public ActionForward buscarGrupo (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		String pantalla ="";
		
		//Definimos el objeto formulario
		grupoForm formulario = (grupoForm)form;
		
		//Recuperamos el vector de titulaciones
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		
		grupo grupo = gGrupos.datosGrupo(request.getParameter("grupo"));
		
		//Si hay titulaciones
		if (grupo != null)
		{
			//Se envia en la peticion el vector de titulaciones
			formulario.setAsigAsoc(grupo.getAsigAsoc());
			formulario.setCodigoLab(String.valueOf(grupo.getCodigoLab()));
			formulario.setDia(grupo.getDia());
			formulario.setHora(grupo.getHora());
			formulario.setObservaciones(grupo.getObservaciones());
			formulario.setPlazas(String.valueOf(grupo.getPlazas()));
			formulario.setPlazasOcupadas(String.valueOf(grupo.getPlazasOcupadas()));
			request.setAttribute("grupoForm", formulario);
			return mapping.findForward("mantenimiento_form");
		
		}
				
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("lista_titulaciones");	
	}
    
    public ActionForward modificarGrupo (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		String pantalla ="";
		
		//Definimos el objeto formulario
		grupoForm formulario = (grupoForm)form;
		
		//Validamos los posibles errores del formulario
	    ActionErrors errores = formulario.validate(mapping, request);
		
		//Recuperamos el vector de titulaciones
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		
		boolean modificado=false;
		
		if (errores.isEmpty()) {
  		   modificado = gGrupos.modificarGrupo(formulario);
  		   if (modificado){
  			   pantalla="grupo_modificado";
  		   }
		}else {
			//Se guardan los errores en la request
	        saveErrors(request, errores);
	        request.setAttribute("grupoForm", formulario);
	        pantalla="mantenimiento_form";
		}
		return mapping.findForward(pantalla);
	}
    
    public ActionForward borrarGrupo (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		grupoForm formulario = (grupoForm)form;
		
		//Recuperamos el vector de titulaciones
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		boolean borrado = gGrupos.borrarGrupo(formulario.getCodigoLab());
		
		//Si se ha borrado con exito
		if (borrado){			
			return mapping.findForward("grupo_borrado");		
		}
		
		//Si no hay titulaciones o no se ha cumplido alguna condición anterior
		return mapping.findForward("mantenimiento_form");		
	}
}
