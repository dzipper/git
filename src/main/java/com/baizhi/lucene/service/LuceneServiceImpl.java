package com.baizhi.lucene.service;

import com.baizhi.lucene.dao.LuceneDao;
import com.baizhi.lucene.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by SK on 2018/8/9.
 */
@Service
public class LuceneServiceImpl implements LuceneService{
    @Autowired
    private LuceneDao luceneDao;

    @Override
    public void addLucene(Product product) {
        product.setId(UUID.randomUUID().toString());
        luceneDao.saveIndex(product);
    }

    @Override
    public List<Product> getProducts(String value) {
      return  luceneDao.queryIndex(value);
    }
}
