package com.example.demo.service;

import com.example.demo.eneity.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lizhiwei
 * @date 2021/11/4 16:27
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private List<Article> list = new ArrayList<>();

    public ArticleServiceImpl() {
        list.add(new Article(1,"java","java从入门到搬砖"));
        list.add(new Article(2,"SQL","SQL从删库到跑路"));

    }
    @Override
    public List<Article> getArticles() {
        return list;
    }
    @Override
    public void deleteArticle(int id) {
        Iterator iter = list.iterator();
        while(iter.hasNext()){
            Article article = (Article)iter.next();
            if(article.getId()==id){
                iter.remove();
            }
        }
    }
}
