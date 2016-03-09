package com.examen;

import java.io.IOException;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Portlet implementation class PortletC
 */
public class PortletC extends GenericPortlet {

    public void init() {
        viewTemplate = getInitParameter("view-template");
    }

    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(viewTemplate, renderRequest, renderResponse);
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
    
    @ProcessEvent(qname="{http://moveToC.com}mensaje")
    public void recogerMiEvento(EventRequest arg0, EventResponse arg1) throws PortletException, IOException {
    	
    	Event evento = arg0.getEvent();
    	Persona persona = (Persona)evento.getValue();

    	String nombre = persona.getNombre();
    	String direccion= persona.getDireccion();
    	String telefono=persona.getTelefono();
    	
    	arg0.setAttribute("nombre", nombre);
    	arg0.setAttribute("direccion", direccion);
    	arg0.setAttribute("telefono", telefono);
    	
    }
 
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(PortletC.class);

}
