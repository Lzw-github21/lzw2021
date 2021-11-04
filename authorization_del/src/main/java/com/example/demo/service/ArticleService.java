package com.example.demo.service;

import com.example.demo.eneity.Article;

import java.util.List;

/**
 * @author lizhiwei
 * @date 2021/11/4 16:28
 */
public interface ArticleService {
    List<Article> getArticles();
    void deleteArticle(int id);
}


