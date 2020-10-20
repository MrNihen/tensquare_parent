package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.QaService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creat by nihen on 2020/10/20 18:43
 */
@RestController
@RequestMapping("problem")
public class QaController {
    @Autowired
    private QaService qaService;
    //1.查询最新问题列表
    @GetMapping("newlist/{id}/{page}/{size}")
    public Result newList(@PathVariable String id,@PathVariable int page,@PathVariable int size){
        //1.1)查询最新问题
        PageResult<Problem> pageResult= qaService.newList(id,page,size);
        //1.2)返回
        return new Result(StatusCode.OK,true,"查询最新问题列表成功！",pageResult);
    }

    //2.查询热门回答问题列表
    @GetMapping("hotlist/{id}/{page}/{size}")
    public Result hotlist(@PathVariable String id,@PathVariable int page,@PathVariable int size){
        //2.1)查询热门回答问题列表
        PageResult<Problem> pageResult= qaService.hotlist(id,page,size);
        //2.2)返回
        return new Result(StatusCode.OK,true,"查询热门回答问题列表成功！",pageResult);
    }
}
