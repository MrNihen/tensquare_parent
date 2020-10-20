package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Creat by nihen on 2020/10/20 18:27
 */
@RestController
@RequestMapping("recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;
    @GetMapping("search/recommend")
    public Result recommend(){
        //1.1)得到推荐职位列表
        List<Recruit> recruitList = recruitService.recommend();
        //1.2)返回
        return new Result(StatusCode.OK,true,"查询推荐列表成功！",recruitList);
    }

    //2.查询最新职位列表
    @GetMapping("search/newlist")
    public Result newlist(){
        //1.1)得到最新职位列表
        List<Recruit> recruitList = recruitService.newlist();
        //1.2)返回
        return new Result(StatusCode.OK,true,"查询最新职位列表成功！",recruitList);
    }

}
