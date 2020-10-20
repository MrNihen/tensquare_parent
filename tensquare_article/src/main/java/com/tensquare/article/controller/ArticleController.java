package com.tensquare.article.controller;

import com.tensquare.article.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creat by nihen on 2020/10/20 19:20
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    //1.文章审核
    @PutMapping("examine/{articleId}")
    public Result examine(@PathVariable String articleId){
        articleService.examine(articleId);
        return new Result(StatusCode.OK,true,"文章审核成功！");
    }

    //2.文章点赞
    @PutMapping("thumbup/{articleId}")
    public Result thumbup(@PathVariable String articleId){
        articleService.thumbup(articleId);
        return new Result(StatusCode.OK,true,"文章点赞成功！");
    }
}
