package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Creat by nihen on 2020/10/21 20:16
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    //1.根据上级ID查询吐槽数据
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
