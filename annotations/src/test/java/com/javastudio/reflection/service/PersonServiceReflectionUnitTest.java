package com.javastudio.reflection.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PersonServiceReflectionUnitTest {

    private Logger logger= LoggerFactory.getLogger(PersonServiceReflectionUnitTest.class);

    @Test
    public void givenPersonService_whenCountConstructors_thenItShouldBeOne() {
        Constructor<?>[] ctors = PersonService.class.getConstructors();
        for (Constructor<?> ctor : ctors) {
            logger.info(ctor.getName());
            logger.info("{}", ctor.getParameterCount());
            Arrays.stream(ctor.getParameterTypes())
                    .map(Type::getTypeName)
                    .forEach(logger::info);
        }
    }
}