package ru.geekbrains.HomeWork_3_7;

public class TestClass {
    @BeforeSuite
    public static void start() {
    System.out.println("start");
    }
    @Test (priority = 3)
    public static void metod1() {
        System.out.println("met 1");
    }
    @Test (priority = 2)
    public static void metod2() {
        System.out.println("met 2");
    }
    @Test (priority = 1)
    public static void metod3() {
        System.out.println("met 3");
    }
    @AfterSuite
    public static void shutdown() {
        System.out.println("shutdown");
    }
}
