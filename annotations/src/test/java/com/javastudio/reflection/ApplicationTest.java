package com.javastudio.reflection;

import com.javastudio.reflection.annotations.Inject;
import com.javastudio.reflection.service.PersonService;
import com.javastudio.reflection.service.ProductService;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Inject
    private ProductService productService;

    @Inject
    private PersonService personService;

    @Test
    public void givenDI_whenInject() {

    }
}