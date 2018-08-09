package com.baizhi.lucene.dao;

import com.baizhi.lucene.entity.Product;
import com.baizhi.lucene.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LucenDaoImpl implements LuceneDao {
    @Override
    public void saveIndex(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromPro = LuceneUtil.getDocFromPro(product);
        try {
            indexWriter.addDocument(docFromPro);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIndex(String id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }

    @Override
    public void updateIndex(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromPro = LuceneUtil.getDocFromPro(product);
        try {
            indexWriter.updateDocument(new Term("id", product.getId()+""), docFromPro);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> queryIndex(String value) {
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Product> products = new ArrayList<>();
        try {
            TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("name", value)), 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Product product = LuceneUtil.getProFromDoc(document);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
