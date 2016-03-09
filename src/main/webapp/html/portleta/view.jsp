<%@page import="com.examen.PortletA"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />

<title>Portlet A</title>

<portlet:actionURL var="urlEnviarEventoAPortletB" name="<%=PortletA.ENVIAR_EVENTO_A_PORTLETB %>"/>
<portlet:actionURL var="urlEnviarEventoAPortletC" name="<%=PortletA.ENVIAR_EVENTO_A_PORTLETC %>"/>

<%
	String nombre = "nombre";
	String direccion = "direccion";
	String telefono = "telefono";
	
	if (request.getAttribute("nombre") != null) {
		nombre = (String)request.getAttribute("nombre");
	}
	if (request.getAttribute("direccion") != null) {
		direccion = (String)request.getAttribute("direccion");
	}
	if (request.getAttribute("telefono") != null) {
		telefono = (String)request.getAttribute("telefono");
	}
%>

<form method="post">
	<p>
		Nombre: <input type="text" name="nombre" placeholder=<%=nombre%>>
	</p>
	<p>
		Direccion: <input type="text" name="direccion" placeholder=<%=direccion%>>
	</p>

	<p>
		Telefono: <input type="text" name="telefono" placeholder=<%=telefono%>>
	</p>
	<p>
		<input type="submit" name="PortletB" value="PortletB" formaction="<%=urlEnviarEventoAPortletB%>">
		<input type="submit" name="PortletC" value="PortletC" formaction="<%=urlEnviarEventoAPortletC%>">
	</p>
</form>

