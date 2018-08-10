package com.baizhi.lucene.test;

import com.baizhi.lucene.entity.Product;
import com.baizhi.lucene.util.LuceneUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by SK on 2018/8/9.
 */
public class Test1 {
    static String text="英爵伦 短袖男 短袖t恤 潮流男士t恤 男装夏装衣服 男生半袖体恤";
    public static void main(String[] args) throws IOException {
//        test(new IKAnalyzer(),text);
//        CharArraySet stopWordsSet = StandardAnalyzer.STOP_WORDS_SET;
//        System.out.println(stopWordsSet);
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Product> products = new ArrayList<>();
        try {
            TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("name","英爵伦 短袖男 短袖t恤 潮流男士t恤 男装夏装衣服 男生半袖体恤")), 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Product product = LuceneUtil.getProFromDoc(document);
                System.out.println(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void  test(Analyzer analyzer, String text) throws IOException {

        System.out.println("当前分词器:--->"+analyzer.getClass().getName());

        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));

        tokenStream.addAttribute(CharTermAttribute.class);

        tokenStream.reset();
        while(tokenStream.incrementToken()){
            CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println(attribute.toString());
        }

        tokenStream.end();
    }
}
