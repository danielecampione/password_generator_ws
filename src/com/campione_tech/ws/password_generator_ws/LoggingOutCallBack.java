package com.campione_tech.ws.password_generator_ws;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 *
 * @author D. Campione
 *
 */
class LoggingOutCallBack implements CachedOutputStreamCallback {

    private static Logger logger = LogManager.getLogger(LoggingOutCallBack.class.getName());

    @Override
    public void onClose(CachedOutputStream cos) {
        try {
            if (cos != null) {
                logger.info("Response XML in out Interceptor : " + IOUtils.toString(cos.getInputStream()));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void onFlush(CachedOutputStream arg0) {
    }
}