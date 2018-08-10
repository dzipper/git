package com.baizhi.lucene.service;

import com.baizhi.lucene.entity.Product;

import java.util.List;

/**
 * Created by SK on 2018/8/9.
 */
public interface LuceneService {
    void addLucene(Product product);
    List<Product> getProducts(String value);
}
