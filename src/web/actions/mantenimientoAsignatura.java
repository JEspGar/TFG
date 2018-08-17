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


public class mantenimientoAsignatura extends DispatchAction
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
       
       Vector profesores = gAsignaturas.listaProfesores();
       contador=profesores.elements();
       ArrayList listaProfesores = new ArrayList();
       while (contador.hasMoreElements()) {
    	   profesor profesor = (profesor)contador.nextElement();
    	   listaProfesores.add(new LabelValueBean((String)profesor.getNombre(), String.valueOf(profesor.getIdProfesor())));
       }
       
       formulario.setTitulacionesList(listaTitulaciones);
       formulario.setProfesoresList(listaProfesores);
       
     //Si hay titulaciones
       if (titulaciones != null && titulaciones.size() > 0 && profesores != null && profesores.size() > 0)
       {
           //Se envia en la peticion el vector de titulaciones
           request.setAttribute("titulacionesList", listaTitulaciones);
           request.setAttribute("profesoresList", listaProfesores);

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
           
           Vector profesores = gAsignaturas.listaProfesores();
           contador=profesores.elements();
           ArrayList listaProfesores = new ArrayList();
           while (contador.hasMoreElements()) {
        	   profesor profesor = (profesor)contador.nextElement();
        	   listaProfesores.add(new LabelValueBean((String)profesor.getNombre(), String.valueOf(profesor.getIdProfesor())));
           }
           
           formulario.setTitulacionesList(listaTitulaciones);
           formulario.setProfesoresList(listaProfesores);
           
           //Se envia en la peticion el vector de titulaciones
           request.setAttribute("titulacionesList", listaTitulaciones);
           
           //Se guardan los errores en la request
           saveErrors(request, errores);

           return mapping.findForward("asignatura_form");

       }else {
    	   asignatura existe= gAsignaturas.comprobarAsignatura(formulario.getCodigo(), formulario.getTitulo(), formulario.getTitulacion());
    	   if (existe.getCodigo()==null) {
    		   profesor profesor = gAsignaturas.datosProfesor(formulario.getResponsable());
    		   formulario.setResponsable(profesor.getNombre());
    		   formulario.setEmail(profesor.getEmail());
    		   formulario.setTelefono(String.valueOf(profesor.getTelefono()));
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
//			formulario.setEmail(asignatura.getEmail());
			SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
			formulario.setFechaFin(formato1.format(asignatura.getFechaFin()));
			formulario.setFechaInicio(formato1.format(asignatura.getFechaInicio()));
			formulario.setResponsable(asignatura.getResponsable());
//			formulario.setTelefono(asignatura.getTelefono());
			formulario.setTitulo(asignatura.getTitulo());
			formulario.setTitulacion(asignatura.getTitulacion());
			
			Vector profesores = gAsignaturas.listaProfesores();
	        Enumeration contador=profesores.elements();
	        ArrayList listaProfesores = new ArrayList();
	        ArrayList listaProfesoresAux = new ArrayList();
	        while (contador.hasMoreElements()) {
	     	   profesor profesor = (profesor)contador.nextElement();
	     	   if (profesor.getNombre().toLowerCase().equals(asignatura.getResponsable().toLowerCase())) {
	     		   listaProfesores.add(new LabelValueBean((String)profesor.getNombre(), String.valueOf(profesor.getIdProfesor())));
	     	   }else {
	     		   listaProfesoresAux.add(new LabelValueBean((String)profesor.getNombre(), String.valueOf(profesor.getIdProfesor())));
	     	   }
	        }
	        listaProfesores.addAll(listaProfesoresAux);
	        
	        formulario.setProfesoresList(listaProfesores);
	        
			request.setAttribute("asignaturasForm", formulario);
			return mapping.findForward("mantenimiento_form");
		
		}
		
		//si se produce un error
		return mapping.findForward("error_generico");		
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
		   profesor profesor = gAsignaturas.datosProfesor(formulario.getResponsable());
  		   formulario.setResponsable(profesor.getNombre());
  		   formulario.setEmail(profesor.getEmail());
  		   formulario.setTelefono(String.valueOf(profesor.getTelefono()));
  		   modificada = gAsignaturas.modificarAsignatura(formulario);
  		   if (modificada){
  			   pantalla="asignatura_modificada";
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
		
		//Buscamos los grupos correspondientes a la asignatura para desactivarlos
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		Vector grupos = gGrupos.listaGrupos(formulario.getCodigo());
		Enumeration contador= grupos.elements();
		boolean gruposDesactivados = true;
		while (contador.hasMoreElements()) {
			grupo grupo=(grupo)contador.nextElement();
			gruposDesactivados = gruposDesactivados && gGrupos.desactivarGrupo(String.valueOf(grupo.getCodigoLab()));
		}
		
		//Marcamos la asignatura como desactivada
		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
		boolean borrada = gAsignaturas.borrarAsignatura(formulario.getCodigo());
		
		//Si se ha borrado con exito
		if (borrada){			
			return mapping.findForward("asignatura_borrada");		
		}
		
		//si se produce un error
		return mapping.findForward("error_generico");		
	}
    
    public ActionForward reactivarAsignatura (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		asignaturasForm formulario = (asignaturasForm)form;
		
		//Buscamos los grupos correspondientes a la asignatura para desactivarlos
//		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
//		Vector grupos = gGrupos.listaGrupos(formulario.getCodigo());
//		Enumeration contador= grupos.elements();
//		boolean gruposDesactivados = true;
//		while (contador.hasMoreElements()) {
//			grupo grupo=(grupo)contador.nextElement();
//			gruposDesactivados = gruposDesactivados && gGrupos.desactivarGrupo(String.valueOf(grupo.getCodigoLab()));
//		}
		
		//Marcamos la asignatura como desactivada
		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver,url,usuario,password);
		boolean borrada = gAsignaturas.activarAsignatura(formulario.getCodigo());
		
		//Si se ha borrado con exito
		if (borrada){			
			return mapping.findForward("asignatura_activada");		
		}
		
		//si se produce un error
		return mapping.findForward("error_generico");			
	}
    
    public ActionForward inicializarAsignatura (ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		//Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");
		
		//Definimos el objeto formulario
		asignaturasForm formulario = (asignaturasForm)form;
		
		//Buscamos los grupos correspondientes a la asignatura para borrarlos
		gestionGrupos gGrupos = new gestionGrupos(driver,url,usuario,password);
		gestionAlumnos gAlumnos = new gestionAlumnos(driver,url,usuario,password);
		Vector grupos = gGrupos.listaGrupos(formulario.getCodigo());
		Enumeration contador= grupos.elements();
		boolean gruposBorrados = true;
		boolean inscripBorradas = true;
		while (contador.hasMoreElements()) {
			grupo grupo=(grupo)contador.nextElement();
			gruposBorrados = gruposBorrados && gGrupos.borrarGrupo(String.valueOf(grupo.getCodigoLab()));
			//Borramos las inscripciones de el grupo correspondiente
			inscripBorradas = inscripBorradas && gAlumnos.borrarInscripcionesGrupo(String.valueOf(grupo.getCodigoLab()));
		}
		
		//Si se ha borrado con exito
		if (gruposBorrados&&inscripBorradas){			
			return mapping.findForward("asignatura_inicializada");		
		}
		
		//si se produce un error
		return mapping.findForward("error_generico");		
	}
}
