package com.campione_tech.ws.password_generator_ws;

import java.io.OutputStream;
import java.util.logging.Logger;

import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

/**
 *
 *
 * @author D. Campione
 *
 */
public class WSLoggingOutInterceptor extends AbstractLoggingInterceptor {

    public WSLoggingOutInterceptor() {
        super(Phase.PRE_STREAM);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        OutputStream os = message.getContent(OutputStream.class);
        CacheAndWriteOutputStream cwos = new CacheAndWriteOutputStream(os);
        message.setContent(OutputStream.class, cwos);

        cwos.registerCallback(new LoggingOutCallBack());
    }

    @Override
    protected Logger getLogger() {
        return null;
    }
}
