package org.poc.cxf.beanvalidation;

import javax.jws.WebService;
import javax.validation.Valid;

@WebService
public interface PersonService {
	
    String getData(@Valid Person request);
}

