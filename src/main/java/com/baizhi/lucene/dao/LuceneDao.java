package com.baizhi.lucene.dao;

import com.baizhi.lucene.entity.Product;

import java.util.List;

public interface LuceneDao {
    public void saveIndex(Product product);

    public void deleteIndex(String id);

    public void updateIndex(Product product);

    public List<Product> queryIndex(String value);
}
