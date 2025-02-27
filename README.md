# Password Generator Web service in Java Enterprise Edition (EE)
A Web service that creates passwords of the desired length.
The Web service is written by using the CXF implementation of the JAX-WS
framework.

Tested with Java 8 (JDK 1.8.0_181) and CXF 2.7.9 (the latest version of CXF
before the version 3) on Apache TomCat 9.0.10.

## Examples
To invoke the Web service you can use a free and open source SOAP client like soapUI.
You can download it from this [link]

[link]: https://www.soapui.org/downloads/latest-release.html

Example of the WSDL URI:
http://localhost:8081/password_generator_ws/services/PasswordGeneratorWSServerPort?wsdl

Example of the SOAP request to create a password of 14 characters:
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pas="http://password_generator_ws.ws.campione_tech.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <pas:generatePassword>
         <!--Optional:-->
         <length>14</length>
      </pas:generatePassword>
   </soapenv:Body>
</soapenv:Envelope>
```

Example of the SOAP request to obtain the version number:
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pas="http://password_generator_ws.ws.campione_tech.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <pas:version/>
   </soapenv:Body>
</soapenv:Envelope>
```

Example of a password generated by the Web service:
```
s7mPKjZoR9G0ZM
```

### Screenshots
![Png](https://i.ibb.co/jknBp7nY/01.png)
![Png](https://i.ibb.co/2YWCcFMy/02.png)
![Png](https://i.ibb.co/whH533SC/03.png)
![Png](https://i.ibb.co/S77mTBJk/04.png)
