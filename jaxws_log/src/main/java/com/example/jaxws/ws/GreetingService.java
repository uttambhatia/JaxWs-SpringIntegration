package com.example.jaxws.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jaxws.bo.GreetingBO;

@Component(value="GreetingService")
@WebService(serviceName="GreetingService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL,parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class GreetingService{
 
	@Autowired
    private GreetingBO greetingBo;
   
    @WebMethod(operationName="greeting")
    public String greeting() {
        return greetingBo.greeting();
    }
 
}