import java.lang.reflect.InvocationTargetException;

public class ClassToTest {
    @BeforeSuite
    static void prepareTest(){
        System.out.println("0");
    }
    @Test(priority = 10)
    static void testWork1(){
        System.out.println("10");
    }

    @Test(priority = 1)
    static void testWork2(){
        System.out.println("1");
    }

    @Test(priority = 5)
    static void testWork3(){
        System.out.println("5.1");
    }
    @Test(priority = 5)
    static void testWork33(){
        System.out.println("5.2");
    }

    @AfterSuite()
    static void endTest(){
        System.out.println("11");
    }

    public static void main(String[] args) {
        ClassToTest c = new ClassToTest();
        try {
            TestClass.start(c.getClass());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
