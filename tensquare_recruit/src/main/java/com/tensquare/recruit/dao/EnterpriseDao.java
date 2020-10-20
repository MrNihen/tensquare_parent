package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Creat by nihen on 2020/10/20 18:25
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String> {
    //1.根据热门企业查询，生成类似：select * from tb_enterprise where ishot=?
    List<Enterprise> findByIshot(String s);
}
