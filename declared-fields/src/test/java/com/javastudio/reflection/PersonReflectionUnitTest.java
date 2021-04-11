package com.javastudio.reflection;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class PersonReflectionUnitTest {

    private final Logger logger = LoggerFactory.getLogger(PersonReflectionUnitTest.class);

    @Test
    public void givenPersonClass_whenGetDeclaredFields_thenTwoFields() {
        Field[] fields = Person.class.getDeclaredFields();
        Assert.assertEquals(fields.length, 2);

        Arrays.stream(fields)
                .map(Field::getName)
                .forEach(logger::info);

        Arrays.stream(fields)
                .map(Field::getType)
                .map(Class::getSimpleName)
                .forEach(logger::info);

        Assert.assertTrue(Arrays.stream(fields).anyMatch(field ->
                field.getName().equals("firstName")
                        && field.getType().equals(String.class)
                        && field.getModifiers() == Modifier.PRIVATE
        ));

        Assert.assertTrue(Arrays.stream(fields).anyMatch(field ->
                field.getName().equals("lastName")
                        && field.getType().equals(String.class)
                        && field.getModifiers() == Modifier.PROTECTED
        ));
    }

    @Test
    public void givenEmployeeClass_whenGetDeclaredFields_thenThreeFields() {
        Field[] personFields = Employee.class.getSuperclass().getDeclaredFields();
        Field[] employeeFields = Employee.class.getDeclaredFields();

        Stream.of(personFields, employeeFields)
                .flatMap(Stream::of)
                .map(field -> field.getType().getSimpleName() + " " + field.getName())
                .forEach(logger::info);
    }
}