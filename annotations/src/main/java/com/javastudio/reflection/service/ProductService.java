package com.javastudio.reflection.service;

import com.javastudio.reflection.annotations.Inject;
import com.javastudio.reflection.repository.ProductRepository;

public class ProductService {
    @Inject
    ProductRepository productRepository;
}
