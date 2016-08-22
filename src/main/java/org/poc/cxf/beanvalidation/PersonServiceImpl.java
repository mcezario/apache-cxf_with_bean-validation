
package org.poc.cxf.beanvalidation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.validation.Valid;

@WebService(endpointInterface = "org.poc.cxf.beanvalidation.PersonService", serviceName = "PersonService")
public class PersonServiceImpl implements PersonService {

	@WebMethod(operationName = "person")
	public String getData(@WebParam(name = "person") @Valid Person request) {
		return "Resquest OK";
	}
}
