package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Creat by nihen on 2020/10/20 18:21
 */
@Service
public class EnterpriseService {
    @Autowired
    //1.进行热门查询
    private EnterpriseDao enterpriseDao;
    public List<Enterprise> hotList() {
        return enterpriseDao.findByIshot("1");
    }
}
