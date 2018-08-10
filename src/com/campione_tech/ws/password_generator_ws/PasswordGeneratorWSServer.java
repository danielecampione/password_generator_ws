package com.campione_tech.ws.password_generator_ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.campione_tech.ws.password_generator_ws.business_logic.PasswordGenerator;

/**
 * 
 * 
 * @author D. Campione
 *
 */
@WebService(targetNamespace = "http://password_generator_ws.ws.campione_tech.com/", portName = "PasswordGeneratorWSServerPort", serviceName = "PasswordGeneratorWSServerService")
public class PasswordGeneratorWSServer {

    private static Logger logger = LogManager.getLogger(PasswordGeneratorWSServer.class.getName());

    @WebMethod(operationName = "generatePassword", action = "urn:GeneratePasswordAction")
    public String generatePassword(@WebParam(name = "length") String length) {
        if (length.trim().length() == 0) {
            logger.error("The password length is not specified.");
            return null;
        }

        int passwordLength;
        try {
            passwordLength = Integer.parseInt(length);
            if (passwordLength < 1) {
                logger.error("Password length must be strictly positive.");
                return null;
            }
        } catch (NumberFormatException ex) {
            logger.error("Unexpected number format: " + length);
            return null;
        }

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        passwordGenerator.initPassword(passwordLength);
        String password = passwordGenerator.getPassword();
        return password;
    }

    @WebMethod(operationName = "version", action = "urn:VersionAction")
    public String version() {
        return "Version 0.0.0.2";
    }
}
