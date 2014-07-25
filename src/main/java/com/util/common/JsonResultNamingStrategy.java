package com.util.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class JsonResultNamingStrategy extends PropertyNamingStrategy{
	
	private static final long serialVersionUID = 5899999905468894356L;
	
	@Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method,
                                             String defaultName){
        return defaultName.toLowerCase();
    }
}
