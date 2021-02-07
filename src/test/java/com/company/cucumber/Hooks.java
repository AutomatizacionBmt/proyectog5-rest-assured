package com.company.cucumber;

import com.company.config.RedmineConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public static void setUp(){
        RedmineConfig.setUp();
    }

    @After
    public  static  void cleanUp(){
        RedmineConfig.cleanUp();
    }
}
