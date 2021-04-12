package com.javastudio.reflection.service;

import com.javastudio.reflection.repository.PersonRepository;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class PersonServiceReflectionUnitTest {

    private Logger logger = LoggerFactory.getLogger(PersonServiceReflectionUnitTest.class);

    @Test
    public void givenPersonService_whenCountConstructors_thenItShouldBeOne() {
        Constructor<?>[] ctors = PersonService.class.getConstructors();
        for (Constructor<?> ctor : ctors) {
            logger.info(ctor.getName());
            logger.info("{}", ctor.getParameterCount());
            Arrays.stream(ctor.getParameterTypes())
                    .map(Type::getTypeName)
                    .forEach(logger::info);
            logger.info(Arrays.toString(ctor.getParameterTypes()));
        }
    }

    @Test
    public void createNewInstance() {

        try {
            Constructor<PersonRepository> ctor = PersonRepository.class.getConstructor(null);
            PersonRepository repository = ctor.newInstance();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void name() throws Exception {
        createObject(PersonService.class);
    }

    private <T> T createObject(Class<T> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor<?> ctor : type.getConstructors()) {
            Class<?>[] parameterTypes = ctor.getParameterTypes();

            Object[] y = Arrays.stream(parameterTypes).map(x -> {
                try {
                    return x.getConstructor().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                return null;
            }).toArray();
            return type.getConstructor(parameterTypes).newInstance(y);
        }
        return type.getConstructor(null).newInstance();
    }


    private void x()  throws Exception{
        Constructor<?> constructor = Class.forName("com.journaldev.reflection.ConcreteClass").getConstructor(int.class);
//getting constructor parameters
        System.out.println(Arrays.toString(constructor.getParameterTypes())); // prints "[int]"

        Object myObj = constructor.newInstance(10);
        Method myObjMethod = myObj.getClass().getMethod("method1", null);
        myObjMethod.invoke(myObj, null); //prints "Method1 impl."

        Constructor<?> hashMapConstructor = Class.forName("java.util.HashMap").getConstructor(null);
        System.out.println(Arrays.toString(hashMapConstructor.getParameterTypes())); // prints "[]"
        HashMap<String, String> myMap = (HashMap<String, String>) hashMapConstructor.newInstance(null);
    }

    @Test
    public void name1() {
        Object object =createObject(PersonService.class.getName());
        System.out.println();
    }

    private Object createObject(String className) {
        try {
            for (Constructor<?> ctor : Class.forName(className).getConstructors()) {
                Class<?>[] parameterTypes = ctor.getParameterTypes();

                Object[] parameters = Arrays.stream(parameterTypes)
                        .map(Class::getName)
                        .map(this::createObject)
                        .toArray();
                return ctor.newInstance(parameters);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class A {
}

class B {
}

class C {
    C(A a) {
    }
}

class D {
    D(A a, B b) {
    }
}
