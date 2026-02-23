package tests;

import org.testng.annotations.Test;
import tests.common.BaseTest;

public class MyFirstTestNG {

    @Test
    public void testMethod() {
        System.out.println("This is REAL TestNG working in IntelliJ!");
    }

    @Test(description = "Test the clearAllScreenshots method that clears the screen shots folder")
    public void test2() {
        BaseTest test = new BaseTest();
        test.clearAllScreenshots();
    }

    @Test(description = "Test the cleanFolder method that clears a single screen shots folder")
    public void test3() {
        BaseTest test = new BaseTest();
        test.cleanFolder("./ManualScreenshots");
    }

}