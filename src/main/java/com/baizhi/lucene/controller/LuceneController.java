package com.baizhi.lucene.controller;

import com.baizhi.lucene.entity.Product;
import com.baizhi.lucene.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by SK on 2018/8/9.
 */
@Controller
public class LuceneController {
    @Autowired
    private LuceneService luceneService;

    //添加文档索引
    @RequestMapping("/addLucene")
    public String addLucene(Product product, MultipartFile pic, HttpServletRequest request){
        //获取当前项目路径
        String projectPath = request.getServletContext().getRealPath("/");
        String uploadPath = projectPath + "image";
        File file = new File(uploadPath);
        //判断文件夹是否存在，不存在则创建
        if (!file.exists()) {
            file.mkdir();
        }
        //获取原文件名
        String filename = pic.getOriginalFilename();
        //在原文件名后加上时间戳，避免名字重复
        filename = filename.substring(0, filename.lastIndexOf(".")) + "-" + new Date().getTime() + filename.substring(filename.lastIndexOf("."));

        product.setPicture(filename);
        //保存文件到相应目录
        try {
            //将文件存入到服务器中
            pic.transferTo(new File(uploadPath, filename));
            //文件添加到索引
            luceneService.addLucene(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    //查找
    @RequestMapping("/showLucene")
    @ResponseBody
    public List<Product> showLucene(String str){
        return luceneService.getProducts(str);
    }
}
