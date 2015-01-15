package com.xml.method;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

public class log4jTests{

    private static Logger logger = Logger.getLogger(log4jTests.class.getClass());

    @Before
    public void setUp(){
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    @Test
    public void logFileTest(){
        logger.info("This a log");
    }
}
