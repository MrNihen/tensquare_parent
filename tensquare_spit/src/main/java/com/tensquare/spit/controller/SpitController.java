package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Creat by nihen on 2020/10/21 20:12
 */
@RestController
@RequestMapping("spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    //1.查询所有吐槽列表
    @GetMapping
    public Result findAll(){
        //1.1)查询所有的吐槽列表
        List<Spit> spitList = spitService.findAll();
        //1.2)返回
        return new Result(StatusCode.OK,true,"查询吐槽列表成功！",spitList);
    }

    //2.查询单个吐槽
    @GetMapping("{spitId}")
    public Result findOne(@PathVariable String spitId){
        //2.1)根据id查询单个吐槽
        Spit spit = spitService.findOne(spitId);
        //2.2)返回
        return new Result(StatusCode.OK,true,"查询单个吐槽成功！",spit);
    }

    //3.添加吐槽
    @PostMapping
    public Result add(@RequestBody Spit spit){
        //3.1)添加吐槽
        spitService.insert(spit);
        //3.2)返回
        return new Result(StatusCode.OK,true,"添加吐槽成功！");
    }

    //4.修改吐槽
    @PutMapping("{spitId}")
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        //4.1)关联主键
        spit.set_id(spitId);
        //4.2)添加吐槽
        spitService.update(spit);
        //4.3)返回
        return new Result(StatusCode.OK,true,"修改吐槽成功！");
    }

    //5.删除吐槽
    @DeleteMapping("{spitId}")
    public Result delete(@PathVariable String spitId){
        //5.1)删除吐槽
        spitService.delete(spitId);
        //5.2)返回
        return new Result(StatusCode.OK,true,"删除吐槽成功！");
    }

    //6.测试吐槽分页
    @GetMapping("comment/{parentid}/{page}/{size}")
    public Result comment(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        //6.1) 根据上级ID查询吐槽数据
        PageResult<Spit> pageResult = spitService.comment(parentid,page,size);
        //6.2) 返回
        return new Result(StatusCode.OK,true,"根据上级ID查询吐槽数据！",pageResult);
    }

    //7.吐槽点赞
    @PutMapping("thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId){
        //7.1)点赞吐槽
        boolean b = spitService.thumbup2(spitId);
        //7.2)判断点赞是否成功
        if(b){
            return new Result(StatusCode.OK,true,"点赞成功！");
        }
        return new Result(StatusCode.ERROR,false,"不能重复点赞！");
    }

}
