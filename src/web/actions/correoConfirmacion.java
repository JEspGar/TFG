/**
 *
 * @author cc
 */

package web.actions;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import javax.mail.*;
import javax.mail.internet.MimeMessage;


import java.util.Date;
import java.util.Properties;

public class correoConfirmacion extends Action
{
    @Override
     public ActionForward execute (ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
    { 
         //Extraemos los datos del ambito de sesión
         HttpSession sesion = request.getSession(false);

         String direccion = (String)sesion.getAttribute("email");

         String asunto = "Inscripcion Laboratorio DCC";
         String mensaje = "El alumno "+(String)sesion.getAttribute("nombre")+" "+(String)sesion.getAttribute("apellidos")+
                          " se ha inscrito en el laboratorio de "+(String)sesion.getAttribute("asignatura")+" de "+
                          (String)sesion.getAttribute("carrera")+" los "+(String)sesion.getAttribute("dia")+
                          " a las "+(String)sesion.getAttribute("hora");

         final String username = "javier.eg.86@gmail.com";
 		final String password = "aht$2630";

 		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
         
//         String hostSmtp = "correo.uah.es";
//         String puertoSMTP = "25";
//         String origen = "noreply@uah.es";
//
//         Properties props = new Properties();
//         props.put("mail.smtp.host", hostSmtp);
//         props.put("mail.from", origen);
//
//         Session session = Session.getInstance(props, null);

         try
         {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO,
                              direccion);
            msg.setSubject(asunto);
            msg.setSentDate(new Date());
            msg.setContent(mensaje, "text/html");
            //msg.setText(mensaje);

            Transport.send(msg);
         }
         catch (MessagingException mex)
         {
            System.out.println("Fallo en el envio del correo: " + mex);
         }

         sesion.removeAttribute("nombre");
         sesion.removeAttribute("apellidos");
         sesion.removeAttribute("asignatura");
         sesion.removeAttribute("carrera");
         sesion.removeAttribute("dia");
         sesion.removeAttribute("hora");
         sesion.removeAttribute("email");

         return mapping.findForward("principal");
    }

    
}
