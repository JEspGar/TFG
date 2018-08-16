<%-- 
    Document   : registro_alumno
    Created on : 17-sep-2009, 10:34:17
    Author     : cc
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
    <title>Mantenimiento de Titulaciones</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/inc/estilos.css">
    <script type="text/javascript">
function submitForm()
{
document.forms[0].action = "mantenimientoTitulacion.do?method=buscarTitulacion"
document.forms[0].submit();
}
</script>
</head>

<body>

    <!-- El contenedor de la cabecera empieza aqui -->
    <div id="header-wrap">
        <!-- El contenido de la cabecera empieza aqui -->
        <div id="header-content">

            <%@include file = "/WEB-INF/cabecera_index.jsp" %>

        </div>
    </div>

     <!-- El contenedor de contenido empieza aquí. -->
    <div id="content-wrap">

           <%@include file = "/admin/contenido/contenido_titulacion_lista_titulaciones.jsp" %>

    </div>

</body>

</html:html>
