package ru.geekbrains.HomeWork_3_7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
	processing(TestClass.class);
    }
    public static void processing(Class c) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = c.getDeclaredMethods();
        List<Method> list = new ArrayList<>();
        for(Method method : methods) {
            if(method.isAnnotationPresent(Test.class)) {
                list.add(method);
            }
        }

        list.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });

        for(Method method : methods) {
            if(method.isAnnotationPresent(BeforeSuite.class)) {
                if(list.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("BeforeSuite exception");
                }
                list.add(0, method);
            }
            if(method.isAnnotationPresent(AfterSuite.class)) {
                if(list.get(list.size() - 1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("AfterSuite exception");
                }
                list.add(method);
            }
        }
        for(Method o : list) {
            o.invoke(null);
        }
    }
}
