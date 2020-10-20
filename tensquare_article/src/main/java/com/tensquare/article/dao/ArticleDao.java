package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Creat by nihen on 2020/10/20 19:21
 */
public interface ArticleDao extends JpaRepository<Article,String> {
    //1. 文章审核
    @Modifying
    @Query(nativeQuery=true,value="update tb_article set state=1 where id=?1")
    void examine(String articleId);

    //2. 文章点赞
    @Modifying
    @Query(nativeQuery=true,value="update tb_article set thumbup=thumbup+1 where id=?1")
    void thumbup(String articleId);
}
