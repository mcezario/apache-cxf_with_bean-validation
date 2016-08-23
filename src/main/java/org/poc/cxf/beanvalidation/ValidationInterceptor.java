package org.poc.cxf.beanvalidation;


import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ValidationInterceptor extends AbstractSoapInterceptor {

    public ValidationInterceptor() {
        super(Phase.MARSHAL);
    }

    public void handleMessage(SoapMessage message) throws Fault {
    	Fault fault = (Fault) message.getContent(Exception.class);
    	if (fault.getCause() instanceof ConstraintViolationException) {
    		fault.setFaultCode(new QName("", String.valueOf("Validation Problem")));
    		Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) fault.getCause()).getConstraintViolations();
    		Element element = fault.getOrCreateDetail();
    		final Document ownerDocument = element.getOwnerDocument();
    		for (ConstraintViolation<?> violation : violations) {
    			String tagName = "";
    			Path propertyPath = violation.getPropertyPath();
    			Iterator<Node> iterator = propertyPath.iterator();
    			while (iterator.hasNext()) {
    				tagName = iterator.next().getName();
    			}
    			Element validationError = ownerDocument.createElement("validation");
    			
    			Element field = validationError.getOwnerDocument().createElement("field");
    			field.setTextContent(tagName);
    			
    			Element value = validationError.getOwnerDocument().createElement("value");
    			Object invalidValue = violation.getInvalidValue();
    			value.setTextContent(invalidValue != null ? invalidValue.toString() : null);
    			
    			Element validation = validationError.getOwnerDocument().createElement("validation");
    			validation.setTextContent(violation.getMessage());
    			
    			validationError.appendChild(field);
    			validationError.appendChild(value);
    			validationError.appendChild(validation);
    			
    			element.appendChild(validationError);
			}
    	}
    }

}
