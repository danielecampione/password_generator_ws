package com.campione_tech.ws.password_generator_ws;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 *
 * @author D. Campione
 *
 */
public class WSLoggingInInterceptor extends AbstractSoapInterceptor {

    private static Logger logger = LogManager.getLogger(WSLoggingInInterceptor.class.getName());

    public WSLoggingInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        // Get the remote address
        HttpServletRequest httpRequest = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        logger.info("Request From the address : " + httpRequest.getRemoteAddr());

        try {
            // Now get the request XML
            InputStream is = message.getContent(InputStream.class);
            CachedOutputStream os = new CachedOutputStream();
            IOUtils.copy(is, os);
            os.flush();
            message.setContent(InputStream.class, os.getInputStream());
            is.close();

            logger.info("The request is: " + IOUtils.toString(os.getInputStream()));
            os.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}