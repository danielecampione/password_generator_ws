package com.campione_tech.ws.password_generator_ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * 
 * @author D. Campione
 *
 */
@WebService(targetNamespace = "http://password_generator_ws.ws.campione_tech.com/", portName = "PasswordGeneratorWSServerPort", serviceName = "PasswordGeneratorWSServerService")
public class PasswordGeneratorWSServer {

    @WebMethod(operationName = "generatePassword", action = "urn:GeneratePasswordAction")
    public String generatePassword(@WebParam(name = "length") String length) {
        return "mocked password";
    }

    @WebMethod(operationName = "version", action = "urn:VersionAction")
    public String version() {
        return "Version 0.0.0.1";
    }
}
