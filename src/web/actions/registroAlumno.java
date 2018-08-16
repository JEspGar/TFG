/**
 *
 * @author cc
 */

package web.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

import web.recursos.*;
import web.forms.*;
import web.data.*;

public class registroAlumno extends DispatchAction {

	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");

		// Definimos el objeto formulario
		registroForm formulario = (registroForm) form;
		grupo objGrupo = new grupo();
		
		//Validamos los posibles errores del formulario
	    ActionErrors errores = formulario.validate(mapping, request);
	    
	    //Si hay errores se devuelve el formulario para que se solucionen
	    if (!errores.isEmpty()){
	           
	           //Se guardan los errores en la request
	           saveErrors(request, errores);
	           request.setAttribute("registroForm", formulario);
	           return mapping.findForward("formulario_registro");

	    }

		// Extraemos el nombre de la asignatura del ambito de sesión
		HttpSession sesion = request.getSession(false);

		String asignatura = (String) sesion.getAttribute("nombre_asignatura");
		String grupo = (String) sesion.getAttribute("codGrupo");
		String carrera = (String) sesion.getAttribute("titulacion");

		sesion.removeAttribute("nombre_asignatura");
		sesion.removeAttribute("codGrupo");
		sesion.removeAttribute("titulacion");

		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver, url, usuario, password);
		String codTitulacion = gAsignaturas.codigoTitAsociado(carrera);
		String codAsignatura = gAsignaturas.codigoAsociado(asignatura, codTitulacion);

		// Comprobamos que el alumno no este inscrito en la asignatura
		gestionAlumnos gAlumnos = new gestionAlumnos(driver, url, usuario, password);
		boolean alumno_inscrito = gAlumnos.alumnoInscrito(formulario.dni, codAsignatura);

		// Si el alumno esta inscrito se le envia un correo para posibilitar el cambio
		// de grupo de laboratorio
		if (alumno_inscrito) {
			String inscripcion = gAlumnos.datosAlumnoInscrito(formulario.dni, codAsignatura);
			String[] datos = inscripcion.split("&");
			inscripcion = "?method=modificarReg&code=" + datos[0] + "ig" + grupo;

			// preparamos el email para la confirmacion del cambio
			String direccion = datos[1];

			String asunto = "Inscripcion Laboratorio DCC - Cambio de grupo";
			String mensaje = "Si deseas modificar tu inscripcion en el grupo de laboratorio por favor abre el siguiente enlace: "
					+ request.getRequestURL() + inscripcion;

			final String username = "javier.eg.86@gmail.com";
			final String pass = "aht$2630";

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, pass);
				}
			});

			// String hostSmtp = "correo.uah.es";
			// String puertoSMTP = "25";
			// String origen = "noreply@uah.es";
			//
			// Properties props = new Properties();
			// props.put("mail.smtp.host", hostSmtp);
			// props.put("mail.from", origen);
			//
			// Session session = Session.getInstance(props, null);

			try {
				MimeMessage msg = new MimeMessage(session);
				msg.setFrom();
				msg.setRecipients(Message.RecipientType.TO, direccion);
				msg.setSubject(asunto);
				msg.setSentDate(new Date());
				msg.setContent(mensaje, "text/html");

				Transport.send(msg);
			} catch (MessagingException mex) {
				System.out.println("Fallo en el envio del correo: " + mex);
			}
			// enviar email con el codigo y devolver formulario con la entrada del codigo
			return mapping.findForward("modificar_inscripcion");
			
		}else{
			
			if ((asignatura != null) && (grupo != null) && (carrera != null)
					&& (!(gAsignaturas.grupoCompleto(grupo)))) {
				// Se inserta el nuevo alumno en el grupo correspondiente en la base de datos
				if (gAlumnos.registrarAlumno(formulario, grupo, codAsignatura, carrera)) {
					// Se actualizan las plazas ocupadas del grupo
					gAsignaturas.actualizarPlazas(grupo);

					request.setAttribute("registroForm", formulario);
					request.setAttribute("asignatura", asignatura);
					request.setAttribute("carrera", carrera);

					gAsignaturas.datosGrupo(objGrupo, grupo);
					request.setAttribute("grupo", objGrupo);
				} else {
					return mapping.findForward("error_alumno_inscrito");
				}

				// Devolvemos el forward correspondiente
				return mapping.findForward("alumno_inscrito");
			} else {
				return mapping.findForward("error_alumno_inscrito");
			}

		}

	}

	//Procedimiento para modificar la inscripcion en grupos de laboratorio
	public ActionForward modificarReg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// Obtenemos los datos de la conexion del fichero web.xml
		String driver = this.getServlet().getServletContext().getInitParameter("driver");
		String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
		String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
		String password = this.getServlet().getServletContext().getInitParameter("password");

		String codigo = request.getParameter("code");
		String[] datos = codigo.split("ig");
		String codInscrip = datos[0];
		String grupo = datos[1];

		// Extraemos el nombre de la asignatura del ambito de sesión
		HttpSession sesion = request.getSession(false);

		// Comprobamos que el alumno no este inscrito en la asignatura y la asignatura no este completa
		gestionAlumnos gAlumnos = new gestionAlumnos(driver, url, usuario, password);
		gestionAsignaturas gAsignaturas = new gestionAsignaturas(driver, url, usuario, password);
		
		String grupoInscrip = gAlumnos.grupoInscripcion(codInscrip);
		
		String mensaje="";
		
		if(gAsignaturas.grupoCompleto(grupo)) {
			//grupo completo
			mensaje="grupo_completo";
		}else {
			if(grupoInscrip.equals(grupo)) {
				//alumno ya inscrito en ese grupo
				mensaje="alumno_inscrito_previamente";
			}else {
				//actualizamos las plazas de los grupos
				gAsignaturas.actualizarPlazas(grupo);
				gAsignaturas.actualizarPlazasBaja(grupoInscrip);
				
				//actualizamos inscripcion, si da fallo volvemos a cambiar las plazas de los grupos
				if(gAlumnos.modificarInscripcion(codInscrip, grupo)) {
					mensaje="inscripcion_modificada";
				}else {
					mensaje="error_inscripcion_modificada";
					gAsignaturas.actualizarPlazas(grupoInscrip);
					gAsignaturas.actualizarPlazasBaja(grupo);
				}
			}
		}		
		return mapping.findForward(mensaje);			
	}
}
