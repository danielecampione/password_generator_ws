
package com.campione_tech.client.password_generator_ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.campione_tech.client.password_generator_ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GeneratePassword_QNAME = new QName("http://password_generator_ws.ws.campione_tech.com/", "generatePassword");
    private final static QName _Version_QNAME = new QName("http://password_generator_ws.ws.campione_tech.com/", "version");
    private final static QName _VersionResponse_QNAME = new QName("http://password_generator_ws.ws.campione_tech.com/", "versionResponse");
    private final static QName _GeneratePasswordResponse_QNAME = new QName("http://password_generator_ws.ws.campione_tech.com/", "generatePasswordResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.campione_tech.client.password_generator_ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VersionResponse }
     * 
     */
    public VersionResponse createVersionResponse() {
        return new VersionResponse();
    }

    /**
     * Create an instance of {@link GeneratePasswordResponse }
     * 
     */
    public GeneratePasswordResponse createGeneratePasswordResponse() {
        return new GeneratePasswordResponse();
    }

    /**
     * Create an instance of {@link GeneratePassword }
     * 
     */
    public GeneratePassword createGeneratePassword() {
        return new GeneratePassword();
    }

    /**
     * Create an instance of {@link Version }
     * 
     */
    public Version createVersion() {
        return new Version();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://password_generator_ws.ws.campione_tech.com/", name = "generatePassword")
    public JAXBElement<GeneratePassword> createGeneratePassword(GeneratePassword value) {
        return new JAXBElement<GeneratePassword>(_GeneratePassword_QNAME, GeneratePassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Version }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://password_generator_ws.ws.campione_tech.com/", name = "version")
    public JAXBElement<Version> createVersion(Version value) {
        return new JAXBElement<Version>(_Version_QNAME, Version.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VersionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://password_generator_ws.ws.campione_tech.com/", name = "versionResponse")
    public JAXBElement<VersionResponse> createVersionResponse(VersionResponse value) {
        return new JAXBElement<VersionResponse>(_VersionResponse_QNAME, VersionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://password_generator_ws.ws.campione_tech.com/", name = "generatePasswordResponse")
    public JAXBElement<GeneratePasswordResponse> createGeneratePasswordResponse(GeneratePasswordResponse value) {
        return new JAXBElement<GeneratePasswordResponse>(_GeneratePasswordResponse_QNAME, GeneratePasswordResponse.class, null, value);
    }

}
