package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.pojo.Gathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Creat by nihen on 2020/10/20 19:31
 */
@Service
@Transactional
public class GatheringService {
    @Autowired
    private GatheringDao gatheringDao;
    //1.根据id查询,将得到的数据放入缓冲，以指定的参数为key，以方法的返回值为值
    @Cacheable(key = "#id",value="gathering")
    public Gathering findById(String id){
        return gatheringDao.findById(id).get();
    }

    //2.修改时清理缓存（要同步缓存）
    @CacheEvict(key = "#gathering.id",value="gathering")
    public void update(Gathering gathering){
        gatheringDao.save(gathering);
    }

    //3.删除时清理缓存（要同步缓存）
    @CacheEvict(key = "#.id",value="gathering")
    public void delete(String id){
        gatheringDao.deleteById(id);
    }
}
