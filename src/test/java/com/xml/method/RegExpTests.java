package com.xml.method;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class RegExpTests {

    private Pattern pattern = Pattern.compile("regPattern");
    private Matcher matcher;

    @Test
    public void RegExpressionTest(){
        String inputStr = "12345regPattern";
        matcher = pattern.matcher(inputStr);
        assertEquals(true, matcher.find());
    }

}
