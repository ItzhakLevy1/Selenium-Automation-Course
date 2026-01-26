package tests;

// 1. Correct import for TestNG
import org.testng.annotations.Test;

public class MyFirstTestNG {

    // 2. The @Test annotation now comes from TestNG
    @Test
    public void testMethod() {
        System.out.println("This is REAL TestNG working in IntelliJ!");
    }
}