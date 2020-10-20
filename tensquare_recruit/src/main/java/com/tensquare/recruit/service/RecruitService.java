package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Creat by nihen on 2020/10/20 18:30
 */
@Service
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;
    //1.查询推荐列表
    public List<Recruit> recommend() {
        return recruitDao.findTop4ByStateOrderByCreatetimeDesc("2");
    }
    //2.查询最新职位列表
    public List<Recruit> newlist() {
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }
}
