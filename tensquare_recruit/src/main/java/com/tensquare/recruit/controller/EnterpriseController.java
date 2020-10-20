package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Creat by nihen on 2020/10/20 18:19
 */
@RestController
@RequestMapping("enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;
    //1.查询热门企业
    @GetMapping("search/hotlist")
    public Result hotList(){
        //1.1)查询热门企业列表
        List<Enterprise> enterprises = enterpriseService.hotList();
        //1.2)返回
        return new Result(StatusCode.OK,true,"查询热门企业成功！",enterprises);

    }
}
