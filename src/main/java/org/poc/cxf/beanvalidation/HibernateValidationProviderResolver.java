package org.poc.cxf.beanvalidation;

import java.util.Collections;
import java.util.List;

import javax.validation.ValidationProviderResolver;

import org.hibernate.validator.HibernateValidator;

public class HibernateValidationProviderResolver implements ValidationProviderResolver {

    @Override
    public List getValidationProviders() {
        return Collections.singletonList(new HibernateValidator());
    }

}
