package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;

/**
 * Creat by nihen on 2020/10/20 19:21
 */
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RedisTemplate redisTemplate;
    //1. 文章审核
    public void examine(String articleId) {
        articleDao.examine(articleId);
    }
    //2.文章点赞
    public void thumbup(String articleId) {
        articleDao.thumbup(articleId);
    }
    //3.根据id查询文章
    public Article findById(String id){
        //3.1)从redis中得到文章对象
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        //3.2)判断是否存在，不存在就从数据库中得到
        if(article == null){
            article = articleDao.findById(id).get();
            //3.3）存放到redis中
            redisTemplate.opsForValue().set("article_"+id,article,10, TimeUnit.HOURS);
            System.out.println("将文章保存到redis中。。。");
        }
        return article;
    }
    //4.修改文章
    public void update(Article article){
        //4.1)从redis中删除文章
        redisTemplate.delete("article_"+article.getId());
        //4.2)修改
        articleDao.save(article);
    }
    //5.删除文章
    public void delete(String articleId){
        //5.1)从redis中删除文章
        redisTemplate.delete("article_"+articleId);
        //5.2)从数据库中删除
        articleDao.deleteById(articleId);
    }
}
