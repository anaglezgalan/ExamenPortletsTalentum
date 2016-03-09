package com.examen;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Portlet implementation class PortletA
 */
public class PortletA extends GenericPortlet {

	public static final String ENVIAR_EVENTO_A_PORTLETB = "enviarEventoAPortletB";

	public static final String ENVIAR_EVENTO_A_PORTLETC = "enviarEventoAPortletC";

	public void init() {
		viewTemplate = getInitParameter("view-template");
	}

	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		include(viewTemplate, renderRequest, renderResponse);
	}

	protected void include(String path, RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		} else {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}

	@ProcessAction(name = ENVIAR_EVENTO_A_PORTLETB)
	public void enviarEventoAPortletB(ActionRequest arg0, ActionResponse arg1) throws PortletException, IOException {

		String nombre = arg0.getParameter("nombre");
		String direccion = arg0.getParameter("direccion");
		String telefono = arg0.getParameter("telefono");

		Persona persona = new Persona(nombre, direccion, telefono);
		
		arg0.setAttribute("nombre", nombre);
		arg0.setAttribute("direccion", direccion);
		arg0.setAttribute("telefono", telefono);
		
		QName qname = new QName("http://moveToB.com", "mensaje", "x");

		arg1.setEvent(qname, persona);
	}

	@ProcessAction(name = ENVIAR_EVENTO_A_PORTLETC)
	public void enviarEventoAPortletC(ActionRequest arg0, ActionResponse arg1) throws PortletException, IOException {

		String nombre = arg0.getParameter("nombre");
		String direccion = arg0.getParameter("direccion");
		String telefono = arg0.getParameter("telefono");
		
		QName qname = new QName("http://moveToC.com", "mensaje", "x");

		Persona persona = new Persona(nombre, direccion, telefono);
		
		arg0.setAttribute("nombre", nombre);
		arg0.setAttribute("direccion", direccion);
		arg0.setAttribute("telefono", telefono);
		
		arg1.setEvent(qname, persona);
		
	}
	
	protected String viewTemplate;

	private static Log _log = LogFactoryUtil.getLog(PortletA.class);

}
