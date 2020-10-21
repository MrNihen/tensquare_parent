package com.tensquare.spit.service;


import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * Creat by nihen on 2020/10/21 20:14
 */
@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    //1.查询吐槽列表
    public List<Spit> findAll() {
        return spitDao.findAll();
    }
    //2.查询单个吐槽
    public Spit findOne(String spitId) {
        return spitDao.findById(spitId).get();
    }
    //3.添加吐槽
    public void insert(Spit spit) {
        //3.1)设置吐槽的主键id
        spit.set_id(idWorker.nextId() + "");
        //3.2)添加到mogo中
        spitDao.insert(spit);
    }
    //4.修改吐槽
    public void update(Spit spit) {
        spitDao.save(spit);
    }
    //5.删除吐槽
    public void delete(String spitId) {
        spitDao.deleteById(spitId);
    }
    //6.根据上级ID查询吐槽数据
    public PageResult<Spit> comment(String parentid, int page, int size) {
        //6.1)进行分页查询
        Page<Spit> spitPage = spitDao.findByParentid(parentid, PageRequest.of(page - 1, size));
        //6.2)返回
        return new PageResult<>(spitPage.getTotalElements(),spitPage.getContent());
    }
    //7.点赞的方法一
    public boolean thumbup(String spitId) {
        //7.1)查询吐槽对象
        Spit spit = spitDao.findById(spitId).get();
        //7.2)如果查询到了就点赞
        Object flag = redisTemplate.opsForValue().get("spit_" + spitId);
        if(flag != null){   //如果有值，就证明点赞过，就返回
            return false;
        }
        //7.3)开始点赞
        spit.setThumbup(spit.getThumbup() + 1);
        //7.4)再保存回去
        spitDao.save(spit);
        //7.5)保存到redis
        redisTemplate.opsForValue().set("spit_" + spitId,"aa");

        //7.6)返回
        return true;
    }
    //8.点赞的方法二
    public boolean thumbup2(String spitId) {
        //8.0)如果查询到了就点赞
        Object flag = redisTemplate.opsForValue().get("spit_" + spitId);
        if(flag != null){
            return false;
        }
        //8.1)定义查询条件变量
        Query query = new Query(Criteria.where("_id").is(spitId));
        //8.2)定义修改的数据
        Update update = new Update();
        update.inc("thumbup",1);
        //8.3)点赞
        mongoTemplate.updateFirst(query,update,"spit");
        //8.4)保存到redis
        redisTemplate.opsForValue().set("spit_" + spitId,"aa");
        //8.5)返回
        return true;
    }
}
