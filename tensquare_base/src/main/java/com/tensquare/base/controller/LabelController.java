package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nihen on 2020/10/19
 */
@RestController
@RequestMapping("label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;
    //1.查询全部标签
    @GetMapping
    public Result findAll(){
        //1.1)查询所有的标签列表
        List<Label> students = labelService.findAll();
        //1.2)返回
        return new Result( StatusCode.OK,true,"查询标签列表成功！",students);
    }
    //2.根据id查询
    // localhost:9001/label/111
    @GetMapping("{labelId}")
    public Result findById(@PathVariable String labelId){
        try {
            //2.1)根据id查询标签对象
            Label label = labelService.findById(labelId);
            //2.2)返回
            return new Result(StatusCode.OK,true,"根据id查询列表成功！",label);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR,false,"根据id查询列表失败！");
        }
    }
    //3.添加标签
    @PostMapping
    public Result add(@RequestBody Label label){
        //3.1)添加标签
        labelService.add(label);
        //3.2)返回
        return new Result(StatusCode.OK,true,"添加标签成功！");
    }
    //4.修改标签
    @PutMapping("{labelId}")
    public Result update(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        //4.1)修改标签
        labelService.update(label);
        //4.2)返回
        return new Result(StatusCode.OK,true,"修改标签成功！");
    }
    //5.删除标签
    @DeleteMapping("{labelId}")
    public Result delete(@PathVariable String labelId){
        labelService.delete(labelId);
        return new Result(StatusCode.OK,true,"删除标签成功！");
    }

    //6.条件查询
    @PostMapping("search")
    public Result search(@RequestBody Label label){
        //6.1)根据条件查询
        List<Label> labels = labelService.search(label);
        //6.2)返回查询结果
        return new Result(StatusCode.OK,true,"条件查询成功！",labels);
    }
    //7.条件查询带分页
    @PostMapping("search/{page}/{size}")
    public Result search(@PathVariable int page,@PathVariable int size,@RequestBody Label label){
        //6.1)根据条件进行查询
        PageResult<Label> pageResult = labelService.search(label,page,size);
        //6.2)返回结果
        return new Result(StatusCode.OK,true,"条件查询(带分页)成功！",pageResult);
    }
}
