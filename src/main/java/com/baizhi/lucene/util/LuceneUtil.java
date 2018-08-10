package com.baizhi.lucene.util;

import com.baizhi.lucene.entity.Product;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LuceneUtil {
    private static Directory directory;
    private static Version version;
    private static Analyzer analyzer;
    private static IndexWriterConfig config;

    static {
        try {
            version = Version.LUCENE_44;
            analyzer = new IKAnalyzer();
            directory = FSDirectory.open(new File("e:/index"));
            config = new IndexWriterConfig(version, analyzer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static IndexWriter getIndexWriter() {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    public static IndexSearcher getIndexSearcher() {
        IndexReader indexReader = null;
        IndexSearcher indexSearcher = null;
        try {
            indexReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(indexReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexSearcher;

    }

    public static void commit(IndexWriter indexWriter) {
        try {
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void rollback(IndexWriter indexWriter) {
        try {
            indexWriter.rollback();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static Document getDocFromPro(Product product) {
        Document document = new Document();
        document.add(new StringField("id", product.getId(), Field.Store.YES));
        document.add(new TextField("name", product.getName(), Field.Store.YES));
        document.add(new DoubleField("price", product.getPrice(), Field.Store.YES));
        document.add(new TextField("content", product.getContent(), Field.Store.YES));
        document.add(new StringField("picture", product.getPicture(), Field.Store.YES));
        document.add(new StringField("status", product.getStatus(), Field.Store.YES));
        document.add(new TextField("location", product.getLocation(), Field.Store.YES));
        Date date = product.getCreateDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String str = sdf.format(date);
        document.add(new StringField("createDate", str, Field.Store.YES));
        return document;
    }

    public static Product getProFromDoc(Document document) {
        String str = document.get("createDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Product product = new Product(document.get("id"), document.get("name"), Double.valueOf(document.get("price")),
                document.get("content"),document.get("picture"),document.get("status"), date,document.get("location"));
        return product;

    }


}
