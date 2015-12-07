package com.baidu.base;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{
	 
	    @Override  
	    protected Object determineCurrentLookupKey() {   
	        return DbContextHolder.getDbType();   
	    }   
	}
