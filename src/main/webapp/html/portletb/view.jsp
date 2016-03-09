<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />

<%
	String nombre = " ";
	String direccion = " ";
	String telefono = " ";
	
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

<form>
	<p>
		Nombre: <input type="text" name="nombre" value=<%=nombre%>>
	</p>
	<p>
		Direccion: <input type="text" name="direccion" value=<%=direccion%>>
	</p>

	<p>
		Telefono: <input type="text" name="telefono" value=<%=telefono%>>
	</p>
</form>